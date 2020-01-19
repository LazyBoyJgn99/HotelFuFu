package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "fu_reg_hou_cla_ser_relation", schema = "HotelFuFu", catalog = "")
public class FuRegHouClaSerRelation implements Serializable {
    private int id;
    private Integer registerId;
    private Integer houClaSerId;

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
    @Column(name = "register_id")
    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    @Basic
    @Column(name = "hou_cla_ser_id")
    public Integer getHouClaSerId() {
        return houClaSerId;
    }

    public void setHouClaSerId(Integer houClaSerId) {
        this.houClaSerId = houClaSerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuRegHouClaSerRelation that = (FuRegHouClaSerRelation) o;
        return id == that.id &&
                Objects.equals(registerId, that.registerId) &&
                Objects.equals(houClaSerId, that.houClaSerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registerId, houClaSerId);
    }
}
