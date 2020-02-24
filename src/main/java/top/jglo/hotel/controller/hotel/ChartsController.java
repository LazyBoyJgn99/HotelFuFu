package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.FuEquip;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuEquipRepository;
import top.jglo.hotel.repository.FuRegisterRepository;
import top.jglo.hotel.service.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private FuRegisterRepository fuRegisterRepository;
    @Resource
    private TokenService tokenService;

    @ApiOperation("销售额月度报表")
    @PostMapping("chartMonth")
    @AuthToken
    @ResponseBody
    public ServerResult chartMonth(HttpServletRequest request,@RequestParam String year) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        int num= fuRegisterRepository.findMonSales("2020-1-1");
        result.setData(num);
        return result;
    }
}
