package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.FuPlace;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuPlaceRepository;
import top.jglo.hotel.service.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author jingening
 */
@Api(tags = "区域控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"place"})
public class PlaceController {

    @Resource
    private FuPlaceRepository fuPlaceRepository;
    @Resource
    private TokenService tokenService;

    @ApiOperation("获取区域列表")
    @PostMapping("getPlaceList")
    @AuthToken
    @ResponseBody
    public ServerResult getPlaceList(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuPlace> placeList=fuPlaceRepository.findByHotelId(hotelId);
        result.setData(placeList);
        return result;
    }
    @PostMapping(value = {"savePlace"})
    @ApiOperation(value = "给酒店添加/修改区域", notes = "给酒店添加区域，place类")
    @ResponseBody
    @AuthToken
    public ServerResult savePlace(@RequestBody FuPlace place,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        place.setHotelId(hotelId);
        place=fuPlaceRepository.save(place);
        result.setData(place);
        return result;
    }
    @PostMapping(value = {"deletePlace"})
    @ApiOperation(value = "删除区域", notes = "删除区域，place类中的id")
    @ResponseBody
    public ServerResult deletePlace(@RequestBody FuPlace place) {
        ServerResult result=new ServerResult();
        place=fuPlaceRepository.findOne(place.getId());
        place.setHotelId(-place.getHotelId());
        place=fuPlaceRepository.save(place);
        result.setData(place);
        return result;
    }
}
