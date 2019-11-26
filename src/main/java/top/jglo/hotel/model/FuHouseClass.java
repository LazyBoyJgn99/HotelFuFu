package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_house_class", schema = "HotelFuFu", catalog = "")
public class FuHouseClass {
    private int id;
    private String content;
    private int hotelId;
    private int bedCapacity;
    private int userCapacity;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "hotel_id")
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Basic
    @Column(name = "bed_capacity")
    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    @Basic
    @Column(name = "user_capacity")
    public int getUserCapacity() {
        return userCapacity;
    }

    public void setUserCapacity(int userCapacity) {
        this.userCapacity = userCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHouseClass that = (FuHouseClass) o;
        return id == that.id &&
                hotelId == that.hotelId &&
                bedCapacity == that.bedCapacity &&
                userCapacity == that.userCapacity &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, hotelId, bedCapacity, userCapacity);
    }
}
