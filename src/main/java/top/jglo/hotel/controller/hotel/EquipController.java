package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.FuEquip;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuEquipRepository;
import top.jglo.hotel.service.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author jingening
 */
@Api(tags = "设备控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"equip"})
public class EquipController {

    @Resource
    private FuEquipRepository fuEquipRepository;
    @Resource
    private TokenService tokenService;

    @ApiOperation("获取设备列表")
    @PostMapping("getEquipList")
    @AuthToken
    @ResponseBody
    public ServerResult getequipList(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuEquip> equipList=fuEquipRepository.findByHotelId(hotelId);
        result.setData(equipList);
        return result;
    }
    @PostMapping(value = {"saveEquip"})
    @ApiOperation(value = "给酒店添加/修改设备", notes = "给酒店添加设备，equip类")
    @ResponseBody
    @AuthToken
    public ServerResult saveequip(@RequestBody FuEquip equip,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        equip.setHotelId(hotelId);
        equip=fuEquipRepository.save(equip);
        result.setData(equip);
        return result;
    }
    @PostMapping(value = {"deleteEquip"})
    @ApiOperation(value = "删除设备", notes = "删除设备，equip类中的id")
    @ResponseBody
    public ServerResult deleteequip(@RequestBody FuEquip equip) {
        ServerResult result=new ServerResult();
        equip=fuEquipRepository.findOne(equip.getId());
        equip.setHotelId(-equip.getHotelId());
        equip=fuEquipRepository.save(equip);
        result.setData(equip);
        return result;
    }
}
