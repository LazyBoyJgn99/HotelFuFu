package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_house", schema = "HotelFuFu", catalog = "")
public class FuHouse {
    private int id;
    private String name;
    private String place;
    private int status;
    private int classId;

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
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "class_id")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHouse fuHouse = (FuHouse) o;
        return id == fuHouse.id &&
                status == fuHouse.status &&
                classId == fuHouse.classId &&
                Objects.equals(name, fuHouse.name) &&
                Objects.equals(place, fuHouse.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place, status, classId);
    }
}
