package top.jglo.hotel.controller.hotel;


import com.jcraft.jsch.SftpException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
import top.jglo.hotel.util.*;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * @author gkirito
 */

@Api(tags = "房间控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"house"})
public class HouseController {

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

    @PostMapping("checkIn")
    @ApiOperation(value = "直接入住（check in）", notes = "直接入住（check in），房间ID不用输入 自动分配，输入 房型ID, 最晚退房时间yyyy-MM-dd HH:mm:ss，入住人ID列表")
    @ResponseBody
    @AuthToken
    public ServerResult checkIn(@RequestBody CheckInInfo checkInInfo,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int workerId=tokenService.getId(request);
        int houseClassId=checkInInfo.getHouseClassId();
        List<FuHouse> houseList=fuHouseRepository.findByStatusAndClassId(1,houseClassId);
        if(houseList.size()>0){
            List<String> userIdList =checkInInfo.getUserIdList();
            FuHouse house=houseList.get(0);
            String commitTime=DateUtil.formatTimestamp(DateUtil.getNowTimestamp(),"yyyy-MM-dd HH:mm:ss");
            result.setMessage(houseService.checkIn(userIdList,commitTime,checkInInfo.getEndTime(),workerId,house));
            result.setData(house);
        }else {
            result.setMessage("暂无可用房间");
        }

        return result;
    }
    @PostMapping("checkInByHouse")
    @ApiOperation(value = "选房入住（check in）", notes = "选房入住（check in），输入 房间ID，最晚退房时间yyyy-MM-dd HH:mm:ss，入住人ID列表")
    @ResponseBody
    @AuthToken
    public ServerResult checkInByHouse(@RequestBody CheckInInfo checkInInfo,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int workerId=tokenService.getId(request);
        int houseId=checkInInfo.getHouseId();
        List<String> userIdList =checkInInfo.getUserIdList();
        FuHouse house=fuHouseRepository.findOne(houseId);
        if(house.getStatus()==1){
            String commitTime=DateUtil.formatTimestamp(DateUtil.getNowTimestamp(),"yyyy-MM-dd HH:mm:ss");
            result.setMessage(houseService.checkIn(userIdList,commitTime,checkInInfo.getEndTime(),workerId,house));
        }else {
            result.setMessage("该房间不可分配");
        }
        result.setData(house);
        return result;
    }
    @PostMapping("checkInSupply")
    @ApiOperation(value = "已开房间加人（check in）", notes = "已开房间加人（check in），输入 房间ID，最晚退房时间yyyy-MM-dd HH:mm:ss，入住人ID列表")
    @ResponseBody
    @AuthToken
    public ServerResult supplyCheckIn(@RequestBody CheckInInfo checkInInfo,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int workerId=tokenService.getId(request);
        int houseId=checkInInfo.getHouseId();
        List<String> userCardIdList =checkInInfo.getUserIdList();
        FuHouse house=fuHouseRepository.findOne(houseId);
        String commitTime=DateUtil.formatTimestamp(DateUtil.getNowTimestamp(),"yyyy-MM-dd HH:mm:ss");
        result.setMessage(houseService.checkIn(userCardIdList,commitTime,checkInInfo.getEndTime(),workerId,house));

        result.setData(house);
        return result;
    }

    @PostMapping("houseReturn")
    @ApiOperation(value = "退房", notes = "退房，输入房间id")
    @ResponseBody
    public ServerResult houseReturn(@RequestBody FuHouse house) {
        ServerResult result=new ServerResult();
        int houseId=house.getId();
        house=fuHouseRepository.findOne(houseId);
        if(house.getStatus()==0){
            //0在住1干净2脏房3停售
            house.setStatus(2);
            //更改房间状态
            fuHouseRepository.save(house);
            //撤除权限
            fuHouseOpenRepository.returnByHouse(houseId);
            result.setMessage("退房成功");
        }else {
            result.setMessage("该房间不可退");
        }
        result.setData(house);
        return result;
    }
    @PostMapping("houseReturnByUser")
    @ApiOperation(value = "退房(根据用户)", notes = "退房，输入用户id")
    @ResponseBody
    public ServerResult houseReturn(@RequestBody FuUser user) {
        ServerResult result=new ServerResult();
        int userId=user.getId();
        fuHouseOpenRepository.returnByUser(userId);
        result.setMessage("用户退房成功");
        return result;
    }
    @ApiOperation("获取房间列表")
    @PostMapping("getHouseList")
    @AuthToken
    @ResponseBody
    public ServerResult getHouseList(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuHouse> houseList=fuHouseRepository.findByHotelId(hotelId);
        result.setData(houseList);
        return result;
    }
    @ApiOperation("获取房型列表")
    @PostMapping("getHouseClassList")
    @AuthToken
    @ResponseBody
    public ServerResult getHouseClassList(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuHouseClass> houseClassList=fuHouseClassRepository.findByHotelId(hotelId);
        result.setData(houseClassList);
        return result;
    }
    @PostMapping(value = {"saveHouseClass"})
    @ApiOperation(value = "给酒店添加/修改房型", notes = "给酒店添加/修改房型，houseClass类")
    @ResponseBody
    @AuthToken
    public ServerResult addHouseClass(@RequestBody FuHouseClass houseClass,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        houseClass.setHotelId(hotelId);
        houseClass=fuHouseClassRepository.save(houseClass);
        result.setData(houseClass);
        return result;
    }
    @PostMapping(value = {"deleteHouseClass"})
    @ApiOperation(value = "删除房型", notes = "删除房型，houseClass类中的id")
    @ResponseBody
    public ServerResult deleteRole(@RequestBody FuHouseClass houseClass) {
        ServerResult result=new ServerResult();
        houseClass=fuHouseClassRepository.findOne(houseClass.getId());
        houseClass.setHotelId(-houseClass.getHotelId());
        houseClass=fuHouseClassRepository.save(houseClass);
        result.setData(houseClass);
        return result;
    }
    @PostMapping(value = {"saveHouse"})
    @ApiOperation(value = "添加/修改房间", notes = "给房型添加/修改房间，house类")
    @ResponseBody
    @AuthToken
    public ServerResult saveHouse(@RequestBody FuHouse house,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        house.setHotelId(hotelId);
        house=fuHouseRepository.save(house);
        result.setData(house);
        return result;
    }
    @PostMapping(value = {"deleteHouse"})
    @ApiOperation(value = "给房型删除房间", notes = "给房型删除房间，house类的id")
    @ResponseBody
    public ServerResult deleteHouse(@RequestBody FuHouse house) {
        ServerResult result=new ServerResult();
        house=fuHouseRepository.findOne(house.getId());
        house.setClassId(-house.getClassId());
        house=fuHouseRepository.save(house);
        result.setData(house);
        return result;
    }
    @PostMapping(value = {"savePrice"})
    @ApiOperation(value = "给房型添加/修改价格", notes = "给房型添加/修改价格，class_price类")
    @ResponseBody
    public ServerResult savePrice(@RequestBody FuHouseClassPrice houseClassPrice) {
        ServerResult result=new ServerResult();
        houseClassPrice=fuHouseClassPriceRepository.save(houseClassPrice);
        result.setData(houseClassPrice);
        return result;
    }
    @PostMapping(value = {"deletePrice"})
    @ApiOperation(value = "给房型删除房间", notes = "给房型删除房间，class_price类的id")
    @ResponseBody
    public ServerResult deletePrice(@RequestBody FuHouseClassPrice houseClassPrice) {
        ServerResult result=new ServerResult();
        fuHouseClassPriceRepository.delete(houseClassPrice.getId());
        return result;
    }

    /**
     *
     * 文件上传
     */
    @ResponseBody
    @PostMapping("saveFile")
    @AuthToken
    public void comImgUpdate(HttpServletRequest request,@RequestParam("file") MultipartFile multipartFile,@RequestParam int houseClassId,@RequestParam int id) throws Exception {
        int hotelId=tokenService.getHotelId(request);
        FuHouseClassImg houseClassImg=new FuHouseClassImg();
        houseClassImg.setClassId(houseClassId);
        houseClassImg.setId(id);
        houseClassImg=fuHouseClassImgRepository.save(houseClassImg);
        id=houseClassImg.getId();
        houseClassImg.setSrc("https://jglo.top:8091/HotelFuFu/house/"+hotelId+"/"+houseClassId+"/"+id+".jpg");
        fuHouseClassImgRepository.save(houseClassImg);
        //        "/usr/share/nginx/image"
        //        "commodity/"+sellerId+"/"+comId
        //        "first.jpg"
        fileUtil.upLoadFile(multipartFile,"/usr/share/nginx/image/HotelFuFu","house/"+hotelId+"/"+houseClassId,id+".jpg");
    }
    @ResponseBody
    @PostMapping("deleteFile")
    public void deleteFile(@RequestParam int id)  {
        FuHouseClassImg houseClassImg=fuHouseClassImgRepository.findOne(id);
        houseClassImg.setClassId(-houseClassImg.getClassId());
        fuHouseClassImgRepository.save(houseClassImg);
    }

}
