package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "fu_role_power_relation", schema = "HotelFuFu", catalog = "")
public class FuRolePowerRelation implements Serializable {
    private int id;
    private Integer roleId;
    private Integer powerId;

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
    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "power_id")
    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuRolePowerRelation that = (FuRolePowerRelation) o;
        return id == that.id &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(powerId, that.powerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, powerId);
    }
}
