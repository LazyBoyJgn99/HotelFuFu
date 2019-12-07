package top.jglo.hotel.coreinterceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.consts.GeneralConstant;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.util.RedisTools;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * 核心拦截器，配置request的一些初始值
 *
 * @author Gkirito
 */
@Component
public class CoreInterceptor implements HandlerInterceptor {

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";
    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";
    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";
    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    @Resource
    private RedisTools redisTools;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println(request.getRequestURI() + "被拦截");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            String token = request.getHeader(httpHeaderName);
            System.out.println("token is "+ token);
            String id = "";
            if (token != null && token.length() != 0) {
                id = redisTools.get(token);
                System.out.println("id is "+ id);
            }
            if (id != null && !GeneralConstant.STRING_NULL.equals(id.trim())) {
                String timeString = redisTools.get(token + id);
                if (timeString != null) {
                    Long tokeBirthTime = Long.valueOf(timeString);
                    System.out.println("token Birth time is: "+ tokeBirthTime);
                    Long diff = System.currentTimeMillis() - tokeBirthTime;
                    System.out.println("token is exist : "+ diff +" ms");
                    if (diff > TokenConstant.TOKEN_RESET_TIME) {
                        redisTools.expire(id+"hotel", TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
                        redisTools.expire(id, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
                        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
                        System.out.println("Reset expire time success!");
                        long newBirthTime = System.currentTimeMillis();
                        redisTools.set(token + id, Long.toString(newBirthTime));
                    }
                }
                request.setAttribute(REQUEST_CURRENT_KEY, id);
                return true;


            } else {
                JSONObject jsonObject = new JSONObject();

                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    jsonObject.put("code", response.getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED+".token验证失败");
                    out = response.getWriter();
                    out.println(jsonObject);

                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }
            }
        }
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
