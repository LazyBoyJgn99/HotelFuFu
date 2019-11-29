package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_house_class_service", schema = "HotelFuFu", catalog = "")
public class FuHouseClassService {
    private int id;
    private int houseClassId;
    private int serviceId;
    private Integer max;
    private int num;
    private int bindUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "house_class_id")
    public int getHouseClassId() {
        return houseClassId;
    }

    public void setHouseClassId(int houseClassId) {
        this.houseClassId = houseClassId;
    }

    @Basic
    @Column(name = "service_id")
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "max")
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "bind_user")
    public int getBindUser() {
        return bindUser;
    }

    public void setBindUser(int bindUser) {
        this.bindUser = bindUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHouseClassService that = (FuHouseClassService) o;
        return id == that.id &&
                houseClassId == that.houseClassId &&
                serviceId == that.serviceId &&
                num == that.num &&
                bindUser == that.bindUser &&
                Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, houseClassId, serviceId, max, num, bindUser);
    }
}
