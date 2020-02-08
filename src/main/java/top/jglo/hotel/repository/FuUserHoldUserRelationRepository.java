package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuEngine;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.FuUserHoldUserRelation;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuUserHoldUserRelationRepository extends JpaRepository<FuUserHoldUserRelation,Integer> { //id序列化,传入id的类型

    FuUserHoldUserRelation findByUserIdAndHoldUserId(int userId,int holdUserId);

    @Query("SELECT u FROM FuUser u ,FuUserHoldUserRelation h WHERE h.userId=?1 and h.holdUserId=u.id")
    List<FuUser> findUserByUserId(int userId);

}