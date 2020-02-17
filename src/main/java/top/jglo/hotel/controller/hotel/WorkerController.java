package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.model.FuPower;
import top.jglo.hotel.model.FuRole;
import top.jglo.hotel.model.FuWorker;
import top.jglo.hotel.model.FuWorkerRoleRelation;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.model.result.WorkerInfo;
import top.jglo.hotel.repository.*;
import top.jglo.hotel.service.TokenService;
import top.jglo.hotel.util.MD5;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gkirito
 */

@Api(tags = "管理员/用户 控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"admin"})
public class WorkerController {


    @Resource
    private FuWorkerRoleRelationRepository fuWorkerRoleRelationRepository;
    @Resource
    private FuWorkerRepository fuWorkerRepository;
    @Resource
    private FuPowerRepository fuPowerRepository;
    @Resource
    private FuRoleRepository fuRoleRepository;
    @Resource
    private TokenGenerator tokenGenerator;
    @Resource
    private RedisTools redisTools;
    @Resource
    private TokenService tokenService;
    @Resource
    private FuUserRepository fuUserRepository;

    @ApiOperation("token验证")
    @PostMapping("token")
    @AuthToken
    @ResponseBody
    public ServerResult token(HttpServletRequest request) {
        System.out.println((String) request.getAttribute("REQUEST_CURRENT_KEY"));
        return new ServerResult();
    }
    @ApiOperation("获取当前登录中的管理员信息")
    @PostMapping("getUserInfo")
    @AuthToken
    @ResponseBody
    public ServerResult getUserInfo(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        FuWorker worker=fuWorkerRepository.findOne(id);
        result.setData(worker);
        return result;
    }
    @ApiOperation("修改当前登录中的管理员信息")
    @PostMapping("saveUserInfo")
    @AuthToken
    @ResponseBody
    public ServerResult getUserInfo(@RequestBody FuWorker worker) {
        ServerResult result=new ServerResult();
        worker=fuWorkerRepository.save(worker);
        result.setData(worker);
        return result;
    }
    @PostMapping(value = {"login"})
    @ApiOperation(value = "登录", notes = "输入参数是username（phone或username或workNum）和pwd")
    @ResponseBody
    public ServerResult login(@RequestBody FuWorker fuWorker) {
        ServerResult result=new ServerResult();
        String username=fuWorker.getUsername();
        String pwd=fuWorker.getPwd();
        String pwdMd5 = MD5.MD5(pwd);
        System.out.println(pwdMd5);
        List<WorkerInfo> workerInfoList=fuWorkerRepository.findByUsernameAndPwd(username,pwdMd5 );
        String id ;
        String token ;
        WorkerInfo workerInfo;
        if(workerInfoList==null||workerInfoList.size()==0){
            result.setMessage("用户名或密码不正确!");
            result.setStatus(false);
            return result;
        }else {
            workerInfo=workerInfoList.get(0);
            id=String.valueOf(workerInfo.getWorker().getId());
            token = tokenGenerator.generate(id, pwdMd5);
            String hotel=String.valueOf(workerInfo.getWorker().getHotelId());
            //获取到登录信息，从数据库获取到账号信息，或者像微信端发起请求得到session_key和openid
            //开始将信息加密，生成token，并存入redis

            //存入radis。分别存入username为key，token为value，token为key，username为value，并设置一样的过期时间，最后设置key为
            //token+username，value为当前时间，方便检查过期
            redisTools.set(id+"hotel",hotel);
            redisTools.expire(id+"hotel", TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            redisTools.set(id,token);
            redisTools.expire(id, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            redisTools.set(token,id);
            redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            long currentTime = System.currentTimeMillis();
            redisTools.set(token + id, Long.toString(currentTime));
        }
        result.setData(workerInfo);
        result.setMessage(token);
        return result;
    }

    @PostMapping(value = {"showPowerList"})
    @ApiOperation(value = "显示权限列表", notes = "显示权限列表")
    @ResponseBody
    public ServerResult showPowerList() {
        ServerResult result=new ServerResult();
        List<FuPower> powerList=fuPowerRepository.findAll();
        result.setData(powerList);
        return result;
    }
    @PostMapping(value = {"showRoleList"})
    @ApiOperation(value = "显示角色列表", notes = "显示该酒店的角色列表，输入hotelId")
    @ResponseBody
    @AuthToken
    public ServerResult showRoleList(HttpServletRequest request) {
        int hotelId=tokenService.getHotelId(request);
        ServerResult result=new ServerResult();
        List<FuRole> roleList=fuRoleRepository.findByHotelId(hotelId);
        result.setData(roleList);
        return result;
    }
    @PostMapping(value = {"saveRole"})
    @ApiOperation(value = "给酒店添加/修改角色", notes = "给酒店添加角色，role类")
    @ResponseBody
    @AuthToken
    public ServerResult saveRole(@RequestBody FuRole role,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        role.setHotelId(hotelId);
        role=fuRoleRepository.save(role);
        result.setData(role);
        return result;
    }
    @PostMapping(value = {"deleteRole"})
    @ApiOperation(value = "删除角色", notes = "删除角色，role类中的id")
    @ResponseBody
    public ServerResult deleteRole(@RequestBody FuRole role) {
        ServerResult result=new ServerResult();
        role=fuRoleRepository.findOne(role.getId());
        role.setHotelId(-role.getHotelId());
        role=fuRoleRepository.save(role);
        result.setData(role);
        return result;
    }

    @ApiOperation("显示员工列表(带权限)")
    @PostMapping("showWorkerInfo")
    @ResponseBody
    @AuthToken
    public ServerResult showWorkerInfo(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<WorkerInfo> workerInfoList=fuWorkerRepository.findInfoListByHotelId(hotelId);
        result.setData(workerInfoList);
        return result;
    }
    @ApiOperation("显示员工列表")
    @PostMapping("showWorker")
    @ResponseBody
    public ServerResult showWorker(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuWorker> workerList=fuWorkerRepository.findByHotelId(hotelId);
        result.setData(workerList);
        return result;
    }
    @PostMapping(value = {"saveWorker"})
    @AuthToken
    @ApiOperation(value = "给酒店添加/修改员工", notes = "给酒店添加/修改员工，worker类")
    @ResponseBody
    public ServerResult saveWorker(@RequestBody FuWorker worker,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        worker.setHotelId(hotelId);
        worker.setPwd(MD5.MD5(worker.getPwd()));
        worker=fuWorkerRepository.save(worker);
        FuWorkerRoleRelation workerRoleRelation =fuWorkerRoleRelationRepository.findByWorkerId(worker.getId());
        if(workerRoleRelation==null){
            workerRoleRelation.setWorkerId(worker.getId());
            workerRoleRelation.setRoleId(10);
            fuWorkerRoleRelationRepository.save(workerRoleRelation);
        }
        result.setData(worker);
        return result;
    }
    @PostMapping(value = {"saveWorkerRole"})
    @ApiOperation(value = "修改员工的角色", notes = "修改员工的角色，fuWorkerRoleRelation类")
    @ResponseBody
    public ServerResult saveWorkerRole(@RequestBody FuWorkerRoleRelation workerRoleRelation) {
        ServerResult result=new ServerResult();
        FuWorkerRoleRelation oldWorkerRoleRelation =fuWorkerRoleRelationRepository.findByWorkerId(workerRoleRelation.getWorkerId());
        if(oldWorkerRoleRelation!=null){
            workerRoleRelation.setId(oldWorkerRoleRelation.getId());
        }
        workerRoleRelation=fuWorkerRoleRelationRepository.save(workerRoleRelation);
        result.setData(workerRoleRelation);
        return result;
    }
    @PostMapping(value = {"deleteWorker"})
    @ApiOperation(value = "删除员工", notes = "删除员工，worker类中的id")
    @ResponseBody
    public ServerResult deleteRole(@RequestBody FuWorker worker) {
        ServerResult result=new ServerResult();
        fuWorkerRepository.delete(worker.getId());
        return result;
    }

}
