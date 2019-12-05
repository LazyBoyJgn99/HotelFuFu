package top.jglo.hotel.service;

import org.springframework.stereotype.Service;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuUserRepository;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {
    @Resource
    public RedisTools redisTools;
    @Resource
    private TokenGenerator tokenGenerator;
    @Resource
    private FuUserRepository fuUserRepository;

    public void userLogin(FuUser user, ServerResult result){
        if(user.getId()==0){
            user=fuUserRepository.save(user);
            result.setMessage("注册成功");
        }
        String id=String.valueOf(user.getId());
        String token = tokenGenerator.generate(id, user.getPhone());
        //获取到登录信息，从数据库获取到账号信息，或者像微信端发起请求得到session_key和openid
        //开始将信息加密，生成token，并存入redis
        //存入radis。分别存入username为key，token为value，token为key，username为value，并设置一样的过期时间，最后设置key为
        //token+username，value为当前时间，方便检查过期
        redisTools.set(id,token);
        redisTools.expire(id, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        redisTools.set(token,id);
        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        long currentTime = System.currentTimeMillis();
        redisTools.set(token + id, Long.toString(currentTime));
        result.setMessage(token);
    }

}
