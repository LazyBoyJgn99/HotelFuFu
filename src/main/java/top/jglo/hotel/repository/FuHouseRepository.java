package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuHouse;
import top.jglo.hotel.model.FuHouseClass;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuHouseRepository extends JpaRepository<FuHouse,Integer> { //id序列化,传入id的类型

    List<FuHouse> findByHotelId(int hotelId);

    List<FuHouse> findByStatusAndClassId(int status,int classId);
}