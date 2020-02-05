package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.FuUserRegisterRelation;


/**
 * @author jingening
 */
@Repository
public interface FuUserRegisterRelationRepository extends JpaRepository<FuUserRegisterRelation,Integer> { //id序列化,传入id的类型

}