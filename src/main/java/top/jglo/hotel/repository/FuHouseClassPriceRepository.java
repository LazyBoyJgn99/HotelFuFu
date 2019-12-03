package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuHouseClassImg;
import top.jglo.hotel.model.FuHouseClassPrice;


/**
 * @author jingening
 */
@Repository
public interface FuHouseClassPriceRepository extends JpaRepository<FuHouseClassPrice,Integer> { //id序列化,传入id的类型

}