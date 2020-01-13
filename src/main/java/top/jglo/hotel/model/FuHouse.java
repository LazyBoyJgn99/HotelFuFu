package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_house", schema = "HotelFuFu", catalog = "")
public class FuHouse {
    private int id;
    private String name;
    private String place;
    private Integer status;
    private String label;
    private Integer classId;
    private Integer hotelId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "class_id")
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "hotel_id")
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHouse fuHouse = (FuHouse) o;
        return id == fuHouse.id &&
                Objects.equals(name, fuHouse.name) &&
                Objects.equals(place, fuHouse.place) &&
                Objects.equals(status, fuHouse.status) &&
                Objects.equals(label, fuHouse.label) &&
                Objects.equals(classId, fuHouse.classId) &&
                Objects.equals(hotelId, fuHouse.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place, status, label, classId, hotelId);
    }
}
