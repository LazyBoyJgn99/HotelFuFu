package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.model.FuPlace;
import top.jglo.hotel.model.result.PlaceNum;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuPlaceRepository;
import top.jglo.hotel.service.TokenService;
import top.jglo.hotel.util.RedisTools;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
    @Resource
    private RedisTools redisTools;

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
    @PostMapping(value = {"addPlaceFlow"})
    @ApiOperation(value = "区域人流量+1", notes = "区域人流量+1,输入id")
    @ResponseBody
    public ServerResult addPlaceFlow(@RequestBody FuPlace place) {
        ServerResult result=new ServerResult();
        String token="place"+place.getId();
        String numStr=redisTools.get(token);
        Integer num=1;
        if(numStr!=null){
            num=Integer.valueOf(numStr)+1;
        }
        redisTools.set(token,String.valueOf(num));
        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME*24, TimeUnit.SECONDS);
        result.setData(num);
        return result;
    }
    @PostMapping(value = {"subPlaceFlow"})
    @ApiOperation(value = "区域人流量-1", notes = "区域人流量-1,输入id")
    @ResponseBody
    public ServerResult subPlaceFlow(@RequestBody FuPlace place) {
        ServerResult result=new ServerResult();
        String token="place"+place.getId();
        String numStr=redisTools.get(token);
        String zero="0";
        Integer num=0;
        if(numStr!=null&&!zero.equals(numStr)){
            num=Integer.valueOf(numStr)-1;
        }
        redisTools.set(token,String.valueOf(num));
        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME*24, TimeUnit.SECONDS);
        result.setData(num);
        return result;
    }
    @ApiOperation("获取区域列表人流量")
    @PostMapping("getPlaceNum")
    @AuthToken
    @ResponseBody
    public ServerResult getPlaceListNum(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuPlace> placeList=fuPlaceRepository.findByHotelId(hotelId);
        List<PlaceNum> placeNumList=new ArrayList<>();
        for (FuPlace place:placeList
        ) {
            String num=redisTools.get("place"+place.getId());
            if(num==null){
                num="0";
            }
            PlaceNum placeNum=new PlaceNum(place,num);
            placeNumList.add(placeNum);
        }
        result.setData(placeNumList);
        return result;
    }
}
