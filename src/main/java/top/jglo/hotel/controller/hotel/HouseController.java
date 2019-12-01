package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.model.FuPower;
import top.jglo.hotel.model.FuRole;
import top.jglo.hotel.model.FuWorker;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.model.result.WorkerInfo;
import top.jglo.hotel.repository.FuPowerRepository;
import top.jglo.hotel.repository.FuRoleRepository;
import top.jglo.hotel.repository.FuWorkerRepository;
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

@Api(tags = "房间控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"house"})
public class HouseController {

    @Autowired
    FuWorkerRepository fuWorkerRepository;
    @Autowired
    FuPowerRepository fuPowerRepository;
    @Autowired
    FuRoleRepository fuRoleRepository;
    @Resource
    private TokenGenerator tokenGenerator;
    @Resource
    private RedisTools redisTools;



}
