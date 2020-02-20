package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuEngine;
import top.jglo.hotel.model.FuHotelServer;


/**
 * @author jingening
 */
@Repository
public interface FuHotelServerRepository extends JpaRepository<FuHotelServer,Integer> { //id序列化,传入id的类型


}