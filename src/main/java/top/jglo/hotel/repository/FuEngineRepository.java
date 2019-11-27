package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuEngine;
import top.jglo.hotel.model.FuUser;


/**
 * @author jingening
 */
@Repository
public interface FuEngineRepository extends JpaRepository<FuEngine,Integer> { //id序列化,传入id的类型

    FuEngine findByNextId(int id);

    FuEngine findByName(String name);
}