package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuHotelServer;
import top.jglo.hotel.model.FuHouseEquip;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuHouseEquipRepository extends JpaRepository<FuHouseEquip,Integer> { //id序列化,传入id的类型

    List<FuHouseEquip> findByHouseId(int houseId);
    
}