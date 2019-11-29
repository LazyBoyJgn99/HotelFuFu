package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.model.FuWorker;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.model.result.WorkerInfo;
import top.jglo.hotel.repository.FuWorkerRepository;
import top.jglo.hotel.repository.TRepository;
import top.jglo.hotel.util.MD5;

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

    @PostMapping(value = {"login"})
    @ApiOperation(value = "登录", notes = "输入参数是username和pwd")
    @ResponseBody
    public ServerResult login(@RequestParam String username,@RequestParam String pwd) {
        ServerResult result=new ServerResult();
        String pwdMD5= MD5.MD5(pwd);
        System.out.println(pwdMD5);
        WorkerInfo workerInfo=fuWorkerRepository.findByUsernameAndPwd(username,pwdMD5);
        result.setData(workerInfo);
        return result;
    }
}
