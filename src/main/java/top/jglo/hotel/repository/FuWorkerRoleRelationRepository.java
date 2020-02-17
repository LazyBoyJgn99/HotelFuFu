package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuWorker;
import top.jglo.hotel.model.FuWorkerRoleRelation;
import top.jglo.hotel.model.result.WorkerInfo;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuWorkerRoleRelationRepository extends JpaRepository<FuWorkerRoleRelation,Integer> { //id序列化,传入id的类型
    FuWorkerRoleRelation findByWorkerId(int workerId);

}