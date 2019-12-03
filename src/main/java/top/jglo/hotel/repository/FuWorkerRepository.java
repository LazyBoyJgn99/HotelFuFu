package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.FuWorker;
import top.jglo.hotel.model.result.WorkerInfo;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuWorkerRepository extends JpaRepository<FuWorker,Integer> { //id序列化,传入id的类型

    @Query("SELECT new top.jglo.hotel.model.result.WorkerInfo(w,r) " +
            "FROM FuWorker w , FuRole r ,FuWorkerRoleRelation wr " +
            "WHERE (w.phone=?1 or w.username=?1 or w.workNum=?1) AND w.pwd=?2 " +
            "AND w.id=wr.workerId AND r.id=wr.roleId")
    List<WorkerInfo> findByUsernameAndPwd(String username, String pwd);

    List<FuWorker> findByHotelId(int hotelId);

    @Query("SELECT new top.jglo.hotel.model.result.WorkerInfo(w,r) " +
            "FROM FuWorker w , FuRole r ,FuWorkerRoleRelation wr " +
            "WHERE w.hotelId=?1 " +
            "AND w.id=wr.workerId AND r.id=wr.roleId ")
    List<WorkerInfo> findInfoListByHotelId(int hotelId);


}