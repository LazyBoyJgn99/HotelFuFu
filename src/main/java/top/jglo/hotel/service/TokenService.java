package top.jglo.hotel.service;

import org.springframework.stereotype.Service;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jingening
 */
@Service
public class TokenService {
    @Resource
    public RedisTools redisTools;
    @Resource
    private TokenGenerator tokenGenerator;

    /**
     * 获取token中的酒店ID
     * @param request
     * @return
     */
    public int getHotelId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String id=redisTools.get(token);
        int hotelId=Integer.valueOf(redisTools.get(id+"hotel"));
        return hotelId;
    }

    /**
     * 获取token中的管理员ID
     * @param request
     * @return
     */
    public int getId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String id=redisTools.get(token);
        return Integer.valueOf(id);
    }
}
