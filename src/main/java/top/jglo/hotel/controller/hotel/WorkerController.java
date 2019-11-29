package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.model.FuPower;
import top.jglo.hotel.model.FuRole;
import top.jglo.hotel.model.FuWorker;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.model.result.WorkerInfo;
import top.jglo.hotel.repository.FuPowerRepository;
import top.jglo.hotel.repository.FuRoleRepository;
import top.jglo.hotel.repository.FuWorkerRepository;
import top.jglo.hotel.repository.TRepository;
import top.jglo.hotel.util.MD5;

import java.util.List;

/**
 * @author gkirito
 */

@Api(tags = "管理员控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"admin"})
public class WorkerController {

    @Autowired
    FuWorkerRepository fuWorkerRepository;
    @Autowired
    FuPowerRepository fuPowerRepository;
    @Autowired
    FuRoleRepository fuRoleRepository;

    @PostMapping(value = {"login"})
    @ApiOperation(value = "登录", notes = "输入参数是username（phone或username或workNum）和pwd")
    @ResponseBody
    public ServerResult login(@RequestBody FuWorker fuWorker) {
        ServerResult result=new ServerResult();
        String username=fuWorker.getUsername();
        String pwd=fuWorker.getPwd();
        String pwdMD5= MD5.MD5(pwd);
        System.out.println(pwdMD5);
        WorkerInfo workerInfo=fuWorkerRepository.findByUsernameAndPwd(username,pwdMD5);
        if(workerInfo==null){
            result.setMessage("用户名或密码不正确!");
            result.setStatus(false);
        }
        result.setData(workerInfo);
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
    public ServerResult showRoleList(@RequestBody FuRole fuRole) {
        int hotelId=fuRole.getHotelId();
        ServerResult result=new ServerResult();
        List<FuRole> roleList=fuRoleRepository.findByHotelId(hotelId);
        result.setData(roleList);
        return result;
    }
    @PostMapping(value = {"addRole"})
    @ApiOperation(value = "给酒店添加角色", notes = "给酒店添加角色，role类")
    @ResponseBody
    public ServerResult addRole(@RequestBody FuRole role) {
        ServerResult result=new ServerResult();
        role=fuRoleRepository.save(role);
        result.setData(role);
        return result;
    }
    @PostMapping(value = {"deleteRole"})
    @ApiOperation(value = "给酒店添加角色", notes = "给酒店添加角色，role类中的id")
    @ResponseBody
    public ServerResult deleteRole(@RequestBody FuRole role) {
        ServerResult result=new ServerResult();
        role=fuRoleRepository.findOne(role.getId());
        role.setHotelId(-role.getHotelId());
        role=fuRoleRepository.save(role);
        result.setData(role);
        return result;
    }
}
