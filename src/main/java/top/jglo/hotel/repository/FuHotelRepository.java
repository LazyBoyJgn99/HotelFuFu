package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuEngine;
import top.jglo.hotel.model.FuHotel;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuHotelRepository extends JpaRepository<FuHotel,Integer> { //id序列化,传入id的类型

    @Query("SELECT h FROM FuHotel h ORDER BY ((h.lng-?1)*(h.lng-?1)+(h.lat-?2)*(h.lat-?2))")
    List<FuHotel> findByAddr (Double lng,Double lat);

    @Query("SELECT h FROM FuHotel h WHERE h.name like ?1")
    List<FuHotel> findByLikeName (String name);
}