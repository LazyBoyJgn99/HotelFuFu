package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuHouseClass;
import top.jglo.hotel.model.FuHouseClassImg;


/**
 * @author jingening
 */
@Repository
public interface FuHouseClassImgRepository extends JpaRepository<FuHouseClassImg,Integer> { //id序列化,传入id的类型

}