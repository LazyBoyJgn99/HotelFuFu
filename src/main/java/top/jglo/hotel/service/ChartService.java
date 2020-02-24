package top.jglo.hotel.service;

import org.springframework.stereotype.Service;
import top.jglo.hotel.model.FuHouse;
import top.jglo.hotel.model.FuHouseOpen;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.repository.FuHouseOpenRepository;
import top.jglo.hotel.repository.FuHouseRepository;
import top.jglo.hotel.repository.FuUserRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jingening
 */
@Service
public class ChartService {
    @Resource
    private FuHouseRepository fuHouseRepository;
    @Resource
    private FuHouseOpenRepository fuHouseOpenRepository;
    @Resource
    private FuUserRepository fuUserRepository;


    public String findSalesBy(String endTime,String startTime){
        String flag="check in成功";
        int i=0;
        return flag;
    }

}
