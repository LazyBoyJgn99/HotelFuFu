package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.FuHouse;
import top.jglo.hotel.model.FuHouseClass;
import top.jglo.hotel.model.FuHouseClassPrice;
import top.jglo.hotel.model.FuRole;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.*;
import top.jglo.hotel.service.TokenService;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

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
public class HouseController {

//    @Resource
//    private FuWorkerRepository fuWorkerRepository;
    @Resource
    private FuHouseClassPriceRepository fuHouseClassPriceRepository;
    @Resource
    private FuHouseClassRepository fuHouseClassRepository;
    @Resource
    private FuHouseRepository fuHouseRepository;
    @Resource
    private TokenService tokenService;

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
    @ApiOperation(value = "给房型添加/修改房间", notes = "给房型添加/修改房间，house类")
    @ResponseBody
    public ServerResult saveHouse(@RequestBody FuHouse house) {
        ServerResult result=new ServerResult();
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

}
