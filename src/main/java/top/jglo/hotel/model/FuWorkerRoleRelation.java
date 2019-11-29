package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_worker_role_relation", schema = "HotelFuFu", catalog = "")
public class FuWorkerRoleRelation {
    private int id;
    private Integer roleId;
    private Integer workerId;

    @Id
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
    @Column(name = "worker_id")
    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuWorkerRoleRelation that = (FuWorkerRoleRelation) o;
        return id == that.id &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(workerId, that.workerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, workerId);
    }
}
