package top.jglo.hotel.service;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import top.jglo.hotel.model.FuHouse;
import top.jglo.hotel.model.FuHouseOpen;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.repository.FuHouseOpenRepository;
import top.jglo.hotel.repository.FuHouseRepository;
import top.jglo.hotel.repository.FuUserRepository;
import top.jglo.hotel.util.RedisTools;
import top.jglo.hotel.util.token.TokenGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jingening
 */
@Service
public class HouseService {
    @Resource
    private FuHouseRepository fuHouseRepository;
    @Resource
    private FuHouseOpenRepository fuHouseOpenRepository;
    @Resource
    private FuUserRepository fuUserRepository;
    /**
     * 根据房型ID获取干净的房间列表
     * @param classId
     * @return
     */
    public List<FuHouse> getCleanHouseByClassId(int classId){
        return fuHouseRepository.findByStatusAndClassId(1,classId);
    }
    public String checkIn(List<String> userIdCardList,String commitTime,String endTime,int workerId,FuHouse house){
        String flag="check in成功";
        int i=0;
        for (String anUserIdCardList : userIdCardList) {
            i++;
            FuUser user = fuUserRepository.findByCardId(anUserIdCardList);
            if(user==null){
                if (flag.equals("check in成功")) {
                    flag = "";
                }
                flag += "用户"+i+"未注册账号。";
            }
            //0在住1干净2脏房3停售
            FuHouseOpen fuHouseOpen = new FuHouseOpen();
            //提交时间
            fuHouseOpen.setCommitTime(commitTime);
            //开始时间
            fuHouseOpen.setStartTime(commitTime);
            //结束时间
            fuHouseOpen.setEndTime(endTime);
            //用户ID
            fuHouseOpen.setUserId(user.getId());
            //操作人ID
            fuHouseOpen.setWorkerId(workerId);
            //状态 0就绪 1限定时间 2结束
            fuHouseOpen.setStatus(1);
            //房间ID
            fuHouseOpen.setHouseId(house.getId());
            fuHouseOpenRepository.save(fuHouseOpen);
            //状态 0在住 1干净 2脏房 3停售
            house.setStatus(0);
            fuHouseRepository.save(house);
        }
        return flag;
    }
}
