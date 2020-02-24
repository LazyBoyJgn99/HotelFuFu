package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.FuEquip;
import top.jglo.hotel.model.result.ChartInfo;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuEquipRepository;
import top.jglo.hotel.repository.FuRegisterRepository;
import top.jglo.hotel.service.ChartService;
import top.jglo.hotel.service.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static top.jglo.hotel.util.HttpUtil.httpRequestJson;


/**
 * @author jingening
 */
@Api(tags = "图表控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"charts"})
public class ChartsController {

    @Resource
    private ChartService chartService;
    @Resource
    private TokenService tokenService;

    @ApiOperation(value = "销售额月度报表", notes = "year只输入年份YYYY")
    @PostMapping("getMonthSales")
    @AuthToken
    @ResponseBody
    public ServerResult chartMonth(HttpServletRequest request,@RequestParam String year) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<ChartInfo> chartInfoList=chartService.getMonthSales(year,hotelId);
        result.setData(chartInfoList);
        return result;
    }
    @ApiOperation(value = "销售额季度报表", notes = "year只输入年份YYYY")
    @PostMapping("getSeasonSales")
    @AuthToken
    @ResponseBody
    public ServerResult getSeasonSales(HttpServletRequest request,@RequestParam String year) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<ChartInfo> chartInfoList=chartService.getSeasonSales(year,hotelId);
        result.setData(chartInfoList);
        return result;
    }
    @ApiOperation(value = "销售额年度报表", notes = "year只输入年份YYYY")
    @PostMapping("getYearSales")
    @AuthToken
    @ResponseBody
    public ServerResult getYearSales(HttpServletRequest request,@RequestParam String endYear,@RequestParam String startYear) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<ChartInfo> chartInfoList=chartService.getYearSales(startYear,endYear,hotelId);
        result.setData(chartInfoList);
        return result;
    }
}
