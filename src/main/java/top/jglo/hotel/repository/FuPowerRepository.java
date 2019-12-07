package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuPower;
import top.jglo.hotel.model.FuWorker;
import top.jglo.hotel.model.result.WorkerInfo;


/**
 * @author jingening
 */
@Repository
public interface FuPowerRepository extends JpaRepository<FuPower,Integer> { //id序列化,传入id的类型

}