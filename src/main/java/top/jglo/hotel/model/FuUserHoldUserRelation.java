package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_user_hold_user_relation", schema = "HotelFuFu", catalog = "")
public class FuUserHoldUserRelation {
    private int id;
    private int userId;
    private int holdUserId;

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
    @Column(name = "hold_user_id")
    public int getHoldUserId() {
        return holdUserId;
    }

    public void setHoldUserId(int holdUserId) {
        this.holdUserId = holdUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuUserHoldUserRelation that = (FuUserHoldUserRelation) o;
        return id == that.id &&
                userId == that.userId &&
                holdUserId == that.holdUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, holdUserId);
    }
}
