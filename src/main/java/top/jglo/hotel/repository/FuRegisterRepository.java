package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuRegister;
import top.jglo.hotel.model.FuRole;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuRegisterRepository extends JpaRepository<FuRegister,Integer> { //id序列化,传入id的类型

    List<FuRegister> findByStartDateAndHotelId(String startDate,int hotelId);

    @Query(value = "SELECT r FROM FuRegister r " +
            "WHERE (r.hotelId=?1 or ?1 is null or ?1 ='') "+
            "and (r.houseClassId=?2 or ?2 is null or ?2 ='') " +
            "and (r.status=?3 or ?3 is null or ?3 ='') " +
            "and (r.commitTime>=?4 or ?4 is null or ?4 ='') " +
            "and (r.commitTime<=?5 or ?5 is null or ?5 ='') " +
            "and (r.startDate<=?6 or ?6 is null or ?6 ='') " +
            "and (r.endDate>=?6 or ?6 is null or ?6 ='') ")
    List<FuRegister> findByFindInfo(int hotelId, int houseClassId, int status, String commitStartTime, String commitEndTime, String liveDate);

}