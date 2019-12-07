package top.jglo.hotel.service;

import org.springframework.stereotype.Service;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class TokenService {
    @Resource
    public RedisTools redisTools;
    @Resource
    private TokenGenerator tokenGenerator;

    public int getHotelId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String id=redisTools.get(token);
        int hotelId=Integer.valueOf(redisTools.get(id+"hotel"));
        return hotelId;
    }

    public int getId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String id=redisTools.get(token);
        return Integer.valueOf(id);
    }
}
