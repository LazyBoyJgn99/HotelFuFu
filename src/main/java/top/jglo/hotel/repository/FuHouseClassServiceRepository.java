package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuHouseClass;
import top.jglo.hotel.model.FuHouseClassService;


/**
 * @author jingening
 */
@Repository
public interface FuHouseClassServiceRepository extends JpaRepository<FuHouseClassService,Integer> { //id序列化,传入id的类型

}