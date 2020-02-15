package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.FuUserRegisterRelation;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuUserRegisterRelationRepository extends JpaRepository<FuUserRegisterRelation,Integer> { //id序列化,传入id的类型

    @Query("SELECT r.userId FROM FuUserRegisterRelation r WHERE r.registerId=?1")
    List<Integer> findUserIdByRegisterId(int registerId);
}