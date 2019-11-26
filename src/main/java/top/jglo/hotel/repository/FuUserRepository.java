package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import top.jglo.hotel.model.FuUser;

import java.io.Serializable;


/**
 * @author jingening
 */
@Repository
public interface FuUserRepository extends JpaRepository<FuUser,Integer> { //id序列化,传入id的类型
    FuUser findById(int id);
}