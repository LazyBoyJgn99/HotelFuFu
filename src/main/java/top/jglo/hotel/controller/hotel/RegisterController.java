package top.jglo.hotel.controller.hotel;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.model.FuEquip;
import top.jglo.hotel.model.FuRegister;
import top.jglo.hotel.model.FuService;
import top.jglo.hotel.model.FuUserRegisterRelation;
import top.jglo.hotel.model.result.RegisterFindInfo;
import top.jglo.hotel.model.result.RegisterInfo;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuEquipRepository;
import top.jglo.hotel.repository.FuRegisterRepository;
import top.jglo.hotel.repository.FuUserRegisterRelationRepository;
import top.jglo.hotel.service.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author jingening
 */
@Api(tags = "订单控制层")
@CrossOrigin
@Controller
@RequestMapping(value = {"register"})
public class RegisterController {

    @Resource
    private FuRegisterRepository fuRegisterRepository;
    @Resource
    private FuUserRegisterRelationRepository fuUserRegisterRelationRepository;
    @Resource
    private TokenService tokenService;
    @ApiOperation("根据日期获取当天开始的订单列表")
    @PostMapping("getRegisterListByStartDate")
    @AuthToken
    @ResponseBody
    public ServerResult getRegisterListByStartDate(@RequestParam String date, HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        List<FuRegister> registerList=fuRegisterRepository.findByStartDateAndHotelId(date,hotelId);
        result.setData(registerList);
        return result;
    }
    @ApiOperation("各种条件查询订单列表")
    @PostMapping("getRegisterList")
    @AuthToken
    @ResponseBody
    public ServerResult getRegisterList(@RequestBody RegisterFindInfo registerFindInfo, HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        System.out.println(registerFindInfo.toString());
        List<FuRegister> registerList=fuRegisterRepository.findByFindInfo(hotelId,registerFindInfo.getHouseClassId(),registerFindInfo.getStatus(),registerFindInfo.getCommitStartTime(),registerFindInfo.getCommitEndTime(),registerFindInfo.getLiveDate());
        result.setData(registerList);
        return result;
    }
    @PostMapping(value = {"saveRegister"})
    @ApiOperation(value = "给酒店添加/修改订单", notes = "给酒店添加/修改订单，register类")
    @ResponseBody
    @AuthToken
    public ServerResult saveRegister(@RequestBody RegisterInfo registerInfo, HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int hotelId=tokenService.getHotelId(request);
        FuRegister register=registerInfo.getRegister();
        register.setHotelId(hotelId);
        register=fuRegisterRepository.save(register);
        List<Integer> userIdList=registerInfo.getUserIdList();
        int registerId=register.getId();
        for (Integer anUserIdList : userIdList) {
            FuUserRegisterRelation userRegisterRelation = new FuUserRegisterRelation();
            userRegisterRelation.setRegisterId(registerId);
            userRegisterRelation.setUserId(anUserIdList);
            fuUserRegisterRelationRepository.save(userRegisterRelation);
        }
        result.setData(register);
        return result;
    }
    @PostMapping(value = {"deleteRegister"})
    @ApiOperation(value = "删除订单", notes = "删除订单，register类中的id")
    @ResponseBody
    public ServerResult deleteRegister(@RequestBody FuRegister register) {
        ServerResult result=new ServerResult();
        register=fuRegisterRepository.findOne(register.getId());
        register.setHotelId(-register.getHotelId());
        register=fuRegisterRepository.save(register);
        result.setData(register);
        return result;
    }
}
