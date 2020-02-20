package top.jglo.hotel.controller.user;


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
@RequestMapping(value = {"uHouse"})
public class UserHouseController {

    @Resource
    private FuHouseEquipRepository fuHouseEquipRepository;
    @Resource
    private FuHotelServerRepository fuHotelServerRepository;

    @PostMapping("showEquipInfoByHouseId")
    @ApiOperation(value = "显示一个房间的设备情况", notes = "显示一个房间的设备情况，输入 房间ID")
    @ResponseBody
    public ServerResult showEquipInfoByHouseId(@RequestBody FuHouse house) {
        ServerResult result=new ServerResult();
        List<FuHouseEquip> houseEquipList=fuHouseEquipRepository.findByHouseId(house.getId());
        result.setData(houseEquipList);
        return result;
    }
    @PostMapping("showHotelServerInfo")
    @ApiOperation(value = "显示一个设备的服务器地址", notes = "显示一个设备的服务器地址，输入 设备ID")
    @ResponseBody
    public ServerResult showHotelServerInfo(@RequestBody FuHouseEquip equip) {
        ServerResult result=new ServerResult();
        int equipId=equip.getId();
        FuHouseEquip houseEquip=fuHouseEquipRepository.findOne(equipId);
        FuHotelServer hotelServer=fuHotelServerRepository.findOne(houseEquip.getServerId());
        result.setData(hotelServer);
        return result;
    }


}
