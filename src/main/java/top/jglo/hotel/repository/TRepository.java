package top.jglo.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import top.jglo.hotel.model.FuWorker;

import java.io.Serializable;


@NoRepositoryBean //不要暴露出来，数据库中没有表和这个接口匹配
public interface TRepository<T,ID extends Serializable> extends JpaRepository<T,ID> { //id序列化,传入id的类型


}