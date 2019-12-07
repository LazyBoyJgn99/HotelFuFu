package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuPlace;
import top.jglo.hotel.model.FuService;

import java.util.List;


/**
 * @author jingening
 */
@Repository
public interface FuPlaceRepository extends JpaRepository<FuPlace,Integer> { //id序列化,传入id的类型

    List<FuPlace> findByHotelId(int hotelId);
}