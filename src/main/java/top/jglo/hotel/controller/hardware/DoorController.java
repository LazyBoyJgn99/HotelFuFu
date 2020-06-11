package top.jglo.hotel.controller.hardware;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.model.*;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.*;

import top.jglo.hotel.util.FileUtil;
import top.jglo.hotel.util.RedisTools;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author gkirito
 */

@Api(tags = "硬件控制层-door-大鹏")
@CrossOrigin
@Controller
@RequestMapping(value = {"door"})
public class DoorController {

    @Resource
    private FuEquipRepository fuEquipRepository;
    @Resource
    public RedisTools redisTools;
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
     * 获取设备号，身份证号
     */
    @ApiOperation(value = "获取身份证", notes = "设备号，身份证号")
    @ResponseBody
    @PostMapping("getIdCard")
    public ServerResult getIdCard(@RequestParam int equipId,@RequestParam String idCard)  {
        return new ServerResult("OK,equipId:"+equipId+",idCard:"+idCard);
    }

    /**
     *
     * 获取设备号，身份证号，房间号
     */
    @ApiOperation(value = "获取身份证", notes = "设备号，身份证号，房间号")
    @ResponseBody
    @PostMapping("getIdCard2")
    public ServerResult getIdCard2(@RequestParam int equipId,@RequestParam String idCard,@RequestParam String roomId)  {
        return new ServerResult("OK,equipId:"+equipId+",idCard:"+idCard+",roomId:"+roomId);
    }

//    /**
//     *
//     * 进出门
//     */
//    @ApiOperation(value = "进出门", notes = "设备号，+1或-1")
//    @ResponseBody
//    @PostMapping("peopleInOut")
//    public ServerResult propleInOut(@RequestParam int equipId,@RequestParam int value) throws Exception {
//        return new ServerResult("OK,equipId:"+equipId+",区域人数加"+value);
//    }
    /**
     *
     * 进出门
     */
    @ApiOperation(value = "进门", notes = "设备号")
    @ResponseBody
    @PostMapping("peopleIn")
    public ServerResult peopleIn(@RequestParam String equipId)  {
        FuEquip equip = fuEquipRepository.findByEquipUid(equipId);
        Integer placeId = equip.getPlaceId();
        String token="place"+placeId;
        String numStr=redisTools.get(token);
        int num=1;
        if(numStr!=null){
            num=Integer.valueOf(numStr)+1;
        }
        redisTools.set(token,String.valueOf(num));
        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME*24, TimeUnit.SECONDS);
        return new ServerResult("OK,equipId:"+equipId+",区域人数:"+num);
    }
    /**
     *
     * 进出门
     */
    @ApiOperation(value = "出门", notes = "设备号")
    @ResponseBody
    @PostMapping("peopleOut")
    public ServerResult peopleOut(@RequestParam String equipId)  {
        FuEquip equip = fuEquipRepository.findByEquipUid(equipId);
        Integer placeId = equip.getPlaceId();
        String token="place"+placeId;
        String numStr=redisTools.get(token);
        String zero = "0";
        int num = 0;
        if(numStr != null&&!zero.equals(numStr)){
            num = Integer.valueOf(numStr)-1;
        }
        redisTools.set(token,String.valueOf(num));
        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME*24, TimeUnit.SECONDS);
        return new ServerResult("OK,equipId:"+equipId+",区域人数:" + num);
    }
}
