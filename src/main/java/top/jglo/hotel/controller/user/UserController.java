package top.jglo.hotel.controller.user;


import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.annotation.AuthToken;
import top.jglo.hotel.consts.TokenConstant;
import top.jglo.hotel.model.*;
import top.jglo.hotel.model.result.HotelInfo;
import top.jglo.hotel.model.result.PlaceNum;
import top.jglo.hotel.model.result.RegisterInfo;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.*;
import top.jglo.hotel.service.HouseService;
import top.jglo.hotel.service.LoginService;
import top.jglo.hotel.service.TokenService;
import top.jglo.hotel.test.FaceEngineTest22;
import top.jglo.hotel.util.DateUtil;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.*;

//import com.arcsoft.face.toolkit.ImageInfo;

//import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/**
 * @author gkirito
 */

@Api(tags = "用户接口-允锦")
@CrossOrigin
@Controller
@RequestMapping(value = {"user"})
public class UserController {

    @Resource
    private FuPlaceRepository fuPlaceRepository;
    @Resource
    private HouseService houseService;
    @Resource
    private FuRegisterRepository fuRegisterRepository;
    @Resource
    private FuHouseClassRepository fuHouseClassRepository;
    @Resource
    private FuHouseRepository fuHouseRepository;
    @Resource
    private FuUserRepository fuUserRepository;
    @Resource
    private FuHotelRepository fuHotelRepository;
    @Resource
    private FuUserHoldUserRelationRepository fuUserHoldUserRelationRepository;
    @Resource
    private FuUserRegisterRelationRepository fuUserRegisterRelationRepository;
    @Resource
    private FaceEngineTest22 faceEngineTest;
    @Resource
    private LoginService loginService;
    @Resource
    private RedisTools redisTools;
    @Resource
    private TokenGenerator tokenGenerator;
    @Resource
    private TokenService tokenService;

    @PostMapping(value = {"login"})
    @ApiOperation(value = "用户登录", notes = "输入target,特征值比较，如果登录成功，返回的message是token,如果没有此人，直接注册", produces = "人脸识别")
    @ResponseBody
    public ServerResult login(@RequestParam String target)  {
        ServerResult result=new ServerResult();
        //获取所有人脸信息，循环比较
        List<FuUser> fuUserList=fuUserRepository.findAll();
        byte[] targetInfo =Base64.getDecoder().decode(target);
        FuUser user=faceEngineTest.findUser(targetInfo,fuUserList);
        loginService.userLogin(user,result);

        String id ;
        String token ;
        id=String.valueOf(user.getId());
        token = "user"+tokenGenerator.generate(id);
        //获取到登录信息，从数据库获取到账号信息，或者像微信端发起请求得到session_key和openid
        //开始将信息加密，生成token，并存入redis
        //存入redis。分别存入username为key，token为value，token为key，username为value，并设置一样的过期时间，最后设置key为
        //token+username，value为当前时间，方便检查过期
        redisTools.set(token,id);
        redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        result.setData(user);
        result.setMessage(token);

        return result;
    }
    @PostMapping(value = {"saveFaceDetail"})
    @ApiOperation(value = "更新人脸信息", notes = "输入target,特征值更新")
    @AuthToken
    @ResponseBody
    public ServerResult saveFaceDetail(HttpServletRequest request,@RequestParam String target)  {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        byte[] targetInfo =Base64.getDecoder().decode(target);
        FuUser user=fuUserRepository.findById(id);
        user.setFaceDetail(targetInfo);
        user=fuUserRepository.save(user);
        result.setData(user);
        return result;
    }
    @PostMapping(value = {"loginByCardId"})
    @ApiOperation(value = "用户身份证登录", notes = "扫描身份证，身份证号登录")
    @ResponseBody
    public ServerResult loginByCardId(@RequestParam String cardId)  {
        ServerResult result=new ServerResult();
        FuUser user=fuUserRepository.findByCardId(cardId);
        if(user==null){
            result.setMessage("用户不存在");
        }else {
            String id ;
            String token ;
            id=String.valueOf(user.getId());
            token = "user"+tokenGenerator.generate(id);
            //获取到登录信息，从数据库获取到账号信息，或者像微信端发起请求得到session_key和openid
            //开始将信息加密，生成token，并存入redis
            //存入redis。分别存入username为key，token为value，token为key，username为value，并设置一样的过期时间，最后设置key为
            //token+username，value为当前时间，方便检查过期
            redisTools.set(token,id);
            redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            result.setData(user);
            result.setMessage(token);
        }
        return result;
    }
    @PostMapping(value = {"loginByPhone"})
    @ApiOperation(value = "用户手机号登录", notes = "输入手机号，手机号登录")
    @ResponseBody
    public ServerResult loginByPhone(@RequestParam String phone)  {
        ServerResult result=new ServerResult();
        FuUser user=fuUserRepository.findByPhone(phone);
        if(user==null){
            result.setMessage("用户不存在");
        }else {
            String id ;
            String token ;
            id=String.valueOf(user.getId());
            token = "user"+tokenGenerator.generate(id);
            //获取到登录信息，从数据库获取到账号信息，或者像微信端发起请求得到session_key和openid
            //开始将信息加密，生成token，并存入redis
            //存入redis。分别存入username为key，token为value，token为key，username为value，并设置一样的过期时间，最后设置key为
            //token+username，value为当前时间，方便检查过期
            redisTools.set(token,id);
            redisTools.expire(token, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            result.setData(user);
            result.setMessage(token);
        }
        return result;
    }
    @ApiOperation("查看绑定的用户")
    @PostMapping("showBindUser")
    @AuthToken
    @ResponseBody
    public ServerResult showBindUser(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        List<FuUser> fuUserList=fuUserHoldUserRelationRepository.findUserByUserId(id);
        FuUser my=fuUserRepository.findOne(id);
        fuUserList.add(my);
        result.setData(fuUserList);
        return result;
    }
    @ApiOperation("解除绑定用户")
    @PostMapping("unBindUser")
    @AuthToken
    @ResponseBody
    public ServerResult unBindUser(HttpServletRequest request,@RequestParam int holdId) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        FuUserHoldUserRelation fuUserHoldUserRelation=fuUserHoldUserRelationRepository.findByUserIdAndHoldUserId(id,holdId);
        fuUserHoldUserRelation.setUserId(-fuUserHoldUserRelation.getUserId());
        fuUserHoldUserRelation=fuUserHoldUserRelationRepository.save(fuUserHoldUserRelation);
        result.setData(fuUserHoldUserRelation);
        result.setMessage("删除成功");
        return result;
    }
    @ApiOperation("添加绑定的用户")
    @PostMapping("bindUser")
    @AuthToken
    @ResponseBody
    public ServerResult bindUser(HttpServletRequest request,@RequestParam String cardId) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        FuUser holdUser=fuUserRepository.findByCardId(cardId);
        if(holdUser!=null){
            int holdId=holdUser.getId();
            FuUserHoldUserRelation fuUserHoldUserRelation=new FuUserHoldUserRelation();
            fuUserHoldUserRelation.setUserId(id);
            fuUserHoldUserRelation.setHoldUserId(holdId);
            FuUserHoldUserRelation fuUserHoldUserRelation1 =fuUserHoldUserRelationRepository.findByUserIdAndHoldUserId(id,holdId);
            if(fuUserHoldUserRelation1!=null){
                fuUserHoldUserRelation=fuUserHoldUserRelationRepository.save(fuUserHoldUserRelation);
            }
            result.setData(fuUserHoldUserRelation);
        }else {
            result.setMessage("该身份未注册，请先注册");
        }

        return result;
    }
    @ApiOperation("根据身份证号查看用户信息")
    @PostMapping("showUserInfoByCardId")
    @ResponseBody
    public ServerResult showUserInfoByCardId(@RequestParam String cardId) {
        ServerResult result=new ServerResult();
        FuUser user=fuUserRepository.findByCardId(cardId);
        result.setData(user);
        return result;
    }
    @ApiOperation("根据手机号查看用户信息")
    @PostMapping("showUserInfoByPhone")
    @ResponseBody
    public ServerResult showUserInfoByPhone(@RequestParam String phone) {
        ServerResult result=new ServerResult();
        FuUser user=fuUserRepository.findByPhone(phone);
        result.setData(user);
        return result;
    }
    @ApiOperation("查看个人用户信息")
    @PostMapping("showUserInfo")
    @AuthToken
    @ResponseBody
    public ServerResult showUserInfo(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        FuUser user=fuUserRepository.findById(id);
        result.setData(user);
        return result;
    }
    @ApiOperation("修改个人用户信息")
    @PostMapping("saveUserInfo")
    @AuthToken
    @ResponseBody
    public ServerResult saveUserInfo(HttpServletRequest request,@RequestBody FuUser user) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        FuUser myUser=fuUserRepository.findById(id);
        result.setMessage("用户id："+id);
        if(myUser==null){
            result.setMessage("用户不存在");
        }
        if(user.getCardId()!=null){
            FuUser oldUser=fuUserRepository.findByCardId(user.getCardId());
            if(oldUser!=null){
                if(oldUser.getId()!=id){
                    result.setMessage("改身份证已经被注册");
                    return result;
                }
            }
            myUser.setCardId(user.getCardId());
        }
        if(user.getSex()!=null){
            myUser.setSex(user.getSex());
        }
        if(user.getName()!=null){
            myUser.setName(user.getName());
        }
        if(user.getPhone()!=null){
            myUser.setPhone(user.getPhone());
        }
        fuUserRepository.save(myUser);
        result.setData(myUser);
        return result;
    }
    @ApiOperation(value = "添加用户", notes = "添加用户，自动设置为联系人")
    @PostMapping("addUser")
    @AuthToken
    @ResponseBody
    public ServerResult addUser(HttpServletRequest request,@RequestBody FuUser user) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        user=fuUserRepository.save(user);
        FuUserHoldUserRelation userHoldUserRelation=new FuUserHoldUserRelation();
        userHoldUserRelation.setUserId(id);
        userHoldUserRelation.setHoldUserId(user.getId());
        fuUserHoldUserRelationRepository.save(userHoldUserRelation);
        result.setData(user);
        return result;
    }
    @ApiOperation(value = "显示酒店信息", notes = "根据经纬度，酒店名字，价格查询")
    @PostMapping("showHotelInfo")
    @ResponseBody
    public ServerResult showHotelInfo(@RequestBody HotelInfo hotelInfo) {
        ServerResult result=new ServerResult();
        List<FuHotel> hotelList;
        if(hotelInfo.getName()!=null){
            hotelList=fuHotelRepository.findByLikeName("%"+hotelInfo.getName()+"%");
        }else if(hotelInfo.getLat()!=null){
            hotelList=fuHotelRepository.findByAddr(hotelInfo.getLng(),hotelInfo.getLat());
        }else if(hotelInfo.getPrice()!=null){
            hotelList=fuHotelRepository.findAll();
        }else {
            hotelList=fuHotelRepository.findAll();

        }
        result.setData(hotelList);
        return result;
    }
    @ApiOperation(value = "获取房型列表", notes = "输入酒店ID，查询房型列表")
    @PostMapping("getHouseClassList")
    @ResponseBody
    public ServerResult getHouseClassList(@RequestParam int hotelId) {
        ServerResult result=new ServerResult();
        List<FuHouseClass> houseClassList=fuHouseClassRepository.findByHotelId(hotelId);
        result.setData(houseClassList);
        return result;
    }
    @ApiOperation(value = "查询房型的可预定数量", notes = "输入房型ID和日期，YYYY-MM-dd")
    @PostMapping("findHouseNum")
    @ResponseBody
    public ServerResult findHouseNum(@RequestParam int houseClassId,@RequestParam String date) {
        ServerResult result=new ServerResult();
        int num=fuHouseRepository.countByClassId(houseClassId);
        int usingNum=fuRegisterRepository.countByStartDateAndHouseClassId(date,houseClassId);
        result.setData(num-usingNum);
        return result;
    }
    @PostMapping(value = {"saveRegister"})
    @ApiOperation(value = "添加/修改订单", notes = "添加/修改订单，register类,需要填写HotelId，UserList，StartTime，EndTime，houseClassId")
    @AuthToken
    @ResponseBody
    public ServerResult saveRegister(@RequestBody RegisterInfo registerInfo,HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        FuRegister register=registerInfo.getRegister();
        register.setUserId(id);
        register.setCommitTime(DateUtil.getTime());
        register.setStatus(0);
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
    @PostMapping(value = {"findRegisterList"})
    @ApiOperation(value = "查询订单列表", notes = "查询订单列表，register类")
    @AuthToken
    @ResponseBody
    public ServerResult findRegisterList(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        List<FuRegister> registerList =fuRegisterRepository.findByUserId(id);
        result.setData(registerList);
        return result;
    }
    @PostMapping(value = {"cancelRegister"})
    @ApiOperation(value = "取消订单", notes = "取消订单，订单ID")
    @ResponseBody
    public ServerResult cancelRegister(@RequestParam int registerId) {
        ServerResult result=new ServerResult();
        FuRegister register=fuRegisterRepository.findOne(registerId);
        register.setStatus(2);
        register=fuRegisterRepository.save(register);
        result.setData(register);
        result.setMessage("取消成功");
        return result;
    }
    @PostMapping("checkIn")
    @ApiOperation(value = "直接入住（check in）", notes = "直接入住（check in）")
    @ResponseBody
    @AuthToken
    public ServerResult checkIn( HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        int workerId=-1;
        String nowDate=DateUtil.getDate();
        FuRegister register=fuRegisterRepository.findByStartDateAndUserId(nowDate,id);

        int houseClassId= register.getHouseClassId();
        List<FuHouse> houseList=fuHouseRepository.findByStatusAndClassId(1,houseClassId);
        if(houseList.size()>0){
            List<Integer> userIdList =fuUserRegisterRelationRepository.findUserIdByRegisterId(register.getId());
            FuHouse house=houseList.get(0);
            String commitTime = DateUtil.formatTimestamp(DateUtil.getNowTimestamp(),"yyyy-MM-dd HH:mm:ss");
            result.setMessage(houseService.checkInByIdList(userIdList,commitTime,register.getEndTime(),workerId,house));
            result.setData(house);
            register.setStatus(1);
            fuRegisterRepository.save(register);
        }else {
            result.setMessage("暂无可用房间");
        }
        return result;
    }
    @PostMapping("findMyHotel")
    @ApiOperation(value = "查询用户当前入住的酒店ID")
    @ResponseBody
    @AuthToken
    public ServerResult findMyHotel(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        String nowDate=DateUtil.getDate();
        FuRegister register=fuRegisterRepository.findByStartDateAndUserId(nowDate,id);
        int hotelId=register.getHotelId();
        result.setData(hotelId);
        return result;
    }
    @PostMapping("showPlaceList")
    @ApiOperation(value = "显示区域列表")
    @ResponseBody
    @AuthToken
    public ServerResult showPlaceList(HttpServletRequest request) {
        ServerResult result=new ServerResult();
        int id=tokenService.getId(request);
        String nowDate=DateUtil.getDate();
        FuRegister register=fuRegisterRepository.findByStartDateAndUserId(nowDate,id);
        int hotelId=register.getHotelId();
        List<FuPlace> placeList=fuPlaceRepository.findByHotelId(hotelId);
        result.setData(placeList);
        return result;
    }
    @ApiOperation("获取区域列表人流量")
    @PostMapping("getPlaceNum")
    @ResponseBody
    public ServerResult getPlaceListNum(@RequestParam int hotelId) {
        ServerResult result=new ServerResult();
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
