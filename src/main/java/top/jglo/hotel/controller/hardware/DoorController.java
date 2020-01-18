package top.jglo.hotel.controller.hardware;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.*;
import top.jglo.hotel.model.result.CheckInInfo;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.*;
import top.jglo.hotel.service.HouseService;
import top.jglo.hotel.service.TokenService;
import top.jglo.hotel.util.DateUtil;
import top.jglo.hotel.util.FileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gkirito
 */

@Api(tags = "房间控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"house"})
public class DoorController {

//    @Resource
//    private FuWorkerRepository fuWorkerRepository;
    @Resource
    private FuHouseClassImgRepository fuHouseClassImgRepository;
    @Resource
    private FuHouseClassPriceRepository fuHouseClassPriceRepository;
    @Resource
    private FuHouseClassRepository fuHouseClassRepository;
    @Resource
    private FuHouseRepository fuHouseRepository;
    @Resource
    private TokenService tokenService;
    @Resource
    private HouseService houseService;
    @Resource
    private FileUtil fileUtil;
    @Resource
    private FuHouseOpenRepository fuHouseOpenRepository;



    /**
     *
     * 文件上传
     */
    @ResponseBody
    @PostMapping("saveFile")
    public ServerResult comImgUpdate(@RequestParam("file") MultipartFile multipartFile,@RequestParam String name) throws Exception {
        fileUtil.upLoadFile(multipartFile,"/usr/share/nginx/image/HotelFuFu","xdp",name+".jpg");
        return new ServerResult("OK,路径：/usr/share/nginx/image/HotelFuFu/xdp/"+name+".jpg 用sftp下载下来校验");
    }


}
