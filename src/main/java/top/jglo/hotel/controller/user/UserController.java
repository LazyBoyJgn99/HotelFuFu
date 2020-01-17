package top.jglo.hotel.controller.user;


import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.enums.ImageFormat;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuEngineRepository;
import top.jglo.hotel.repository.FuUserRepository;
import top.jglo.hotel.service.LoginService;
import top.jglo.hotel.test.FaceEngineTest22;
import top.jglo.hotel.util.BinaryConversion;
import top.jglo.hotel.util.FaceEngineUtil;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

//import com.arcsoft.face.toolkit.ImageInfo;

//import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/**
 * @author gkirito
 */

@Api(tags = "用户接口-允锦")
@CrossOrigin
@Controller
@RequestMapping(value = {"user"})
public class UserController {

    @Resource
    private FuUserRepository fuUserRepository;
    @Resource
    private FuEngineRepository fuEngineRepository;
    @Resource
    private FaceEngineTest22 faceEngineTest;
    @Resource
    private LoginService loginService;

    @PostMapping(value = {"login"})
    @ApiOperation(value = "用户登录", notes = "输入target,特征值比较，如果登录成功，返回的message是token,如果没有此人，直接注册", produces = "人脸识别")
    @ResponseBody
    public ServerResult login(@RequestParam byte[] target)  {
        ServerResult result=new ServerResult();
        //获取所有人脸信息，循环比较
        List<FuUser> fuUserList=fuUserRepository.findAll();
        System.out.println("接收到的数据："+Arrays.toString(target));
        FuUser user=faceEngineTest.findUser(target,fuUserList);
        loginService.userLogin(user,result);
        result.setData(user);
        System.out.println("最后输出的数据："+Arrays.toString(user.getFaceDetail()));
        return result;
    }
}
