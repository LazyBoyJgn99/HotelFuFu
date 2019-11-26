package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_user_register_relation", schema = "HotelFuFu", catalog = "")
public class FuUserRegisterRelation {
    private int id;
    private int userId;
    private int registerId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "register_id")
    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuUserRegisterRelation that = (FuUserRegisterRelation) o;
        return id == that.id &&
                userId == that.userId &&
                registerId == that.registerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, registerId);
    }
}
