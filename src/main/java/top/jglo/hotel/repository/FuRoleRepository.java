package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuPower;
import top.jglo.hotel.model.FuRole;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuRoleRepository extends JpaRepository<FuRole,Integer> { //id序列化,传入id的类型
    List<FuRole> findByHotelId(int hotelId);
}