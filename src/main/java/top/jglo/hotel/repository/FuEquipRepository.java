package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuEquip;
import top.jglo.hotel.model.FuPlace;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuEquipRepository extends JpaRepository<FuEquip,Integer> { //id序列化,传入id的类型

    List<FuEquip> findByHotelId(int hotelId);

    FuEquip findByEquipUid(String equipUId);
}