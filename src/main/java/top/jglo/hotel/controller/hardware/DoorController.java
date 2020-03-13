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

@Api(tags = "硬件控制层-door-大鹏")
@CrossOrigin
@Controller
@RequestMapping(value = {"door"})
public class DoorController {

    @Resource
    private FileUtil fileUtil;
    /**
     *
     * 文件上传
     */
    @ResponseBody
    @PostMapping("saveFileWithName")
    public ServerResult saveFileWithName(@RequestParam("file") MultipartFile multipartFile,@RequestParam String name) throws Exception {
        fileUtil.upLoadFile(multipartFile,"/usr/share/nginx/image/HotelFuFu","xdp",name+".jpg");
        return new ServerResult("OK,路径：/usr/share/nginx/image/HotelFuFu/xdp/"+name+".jpg 用sftp下载下来校验");
    }
    /**
     *
     * 文件上传
     */
    @ResponseBody
    @PostMapping("saveFile")
    public ServerResult saveFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String name=multipartFile.getName();
        fileUtil.upLoadFile(multipartFile,"/usr/share/nginx/image/HotelFuFu","xdp",name+".jpg");
        return new ServerResult("OK,路径：/usr/share/nginx/image/HotelFuFu/xdp/"+name+".jpg 用sftp下载下来校验");
    }
    /**
     *
     * 获取身份证
     */
    @ResponseBody
    @PostMapping("getIdCard")
    public ServerResult getIdCard(@RequestParam int equipId,@RequestParam String idCard) throws Exception {
        return new ServerResult("OK,equipId:"+equipId+",idCard:"+idCard);
    }

}
