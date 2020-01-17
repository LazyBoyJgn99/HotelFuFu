package top.jglo.hotel.controller;


import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author gkirito
 */

@Api(tags = "事例")
@CrossOrigin
@Controller
@RequestMapping(value = {"example"})
public class ExController {


    @Resource
    private TokenGenerator tokenGenerator;

    @Resource
    private RedisTools redisTools;

    @RequestMapping(value = {"test1"})
    @ApiOperation(value = "测试1", notes = "采用RequestParam的形式", produces = "application/json,application/xml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id号", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @ResponseBody
    public ServerResult findUser(@RequestParam int id, @RequestParam String pwd) {
        ServerResult result=new ServerResult();
        result.setMessage(id+","+pwd);
        return result;
    }

    @RequestMapping(value = {"test2"})
    @ApiOperation(value = "测试2", notes = "采用RequestBody的形式", produces = "application/json,application/xml")
    @ResponseBody
    public ServerResult addWorker(@ApiParam(value = "user类") @RequestBody ServerResult result) {
        return result;
    }


    @RequestMapping(value = {"login"})
    @ApiOperation(value = "登录测试", notes = "登录demo", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
    })
    @ResponseBody
    public ServerResult exLogin(String username,String password) {
        //微信登录结构类似，username为微信 openid，password为服务器请求微信官方后得到的session_key

        //获取到登录信息，从数据库获取到账号信息，或者像微信端发起请求得到session_key和openid
        //开始将信息加密，生成token，并存入redis

        //存入radis。分别存入username为key，token为value，token为key，username为value，并设置一样的过期时间，最后设置key为
        //token+username，value为当前时间，方便检查过期
        String token = tokenGenerator.generate(username, password);
        redisTools.set(username,token);
        redisTools.expire(username, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        redisTools.set(token,username);
        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        Long currentTime = System.currentTimeMillis();
        redisTools.set(token + username,currentTime.toString());
        return new ServerResult(token);
    }

    @ApiOperation("测试token接口")
    @PostMapping("tokentest")
    @AuthToken
    @ResponseBody
    public ServerResult test(Integer id, HttpServletRequest request) {
        System.out.println((String) request.getAttribute("REQUEST_CURRENT_KEY"));
        System.out.println("id:"+id);
        return new ServerResult(String.valueOf(id));
    }
}
