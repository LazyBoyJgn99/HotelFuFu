package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.FuHouse;
import top.jglo.hotel.model.FuHouseClass;
import top.jglo.hotel.model.FuHouseClassPrice;
import top.jglo.hotel.model.FuService;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuHouseClassPriceRepository;
import top.jglo.hotel.repository.FuHouseClassRepository;
import top.jglo.hotel.repository.FuHouseRepository;
import top.jglo.hotel.repository.FuServiceRepository;
import top.jglo.hotel.service.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;



/**
 * @author jingening
 */
@Api(tags = "房间控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"service"})
public class ServiceController {

    @Resource
    private FuServiceRepository fuServiceRepository;
    @Resource
    private TokenService tokenService;

    @ApiOperation("获取服务列表")
    @PostMapping("getServiceList")
    @AuthToken
    @ResponseBody
    public ServerResult getServiceList(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuService> serviceList=fuServiceRepository.findByHotelId(hotelId);
        result.setData(serviceList);
        return result;
    }
    @PostMapping(value = {"saveService"})
    @ApiOperation(value = "给酒店添加/修改服务", notes = "给酒店添加服务，role类")
    @ResponseBody
    @AuthToken
    public ServerResult saveRole(@RequestBody FuService service,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        service.setHotelId(hotelId);
        service=fuServiceRepository.save(service);
        result.setData(service);
        return result;
    }
    @PostMapping(value = {"deleteService"})
    @ApiOperation(value = "删除服务", notes = "删除服务，role类中的id")
    @ResponseBody
    public ServerResult deleteRole(@RequestBody FuService service) {
        ServerResult result=new ServerResult();
        service=fuServiceRepository.findOne(service.getId());
        service.setHotelId(-service.getHotelId());
        service=fuServiceRepository.save(service);
        result.setData(service);
        return result;
    }



}
