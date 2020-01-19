package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "fu_place", schema = "HotelFuFu", catalog = "")
public class FuPlace implements Serializable {
    private int id;
    private String name;
    private int hotelId;
    private String content;
    private Integer capacity;
    private Integer peopleNum;

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
    @Column(name = "hotel_id")
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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
    @Column(name = "capacity")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "people_num")
    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuPlace fuPlace = (FuPlace) o;
        return id == fuPlace.id &&
                hotelId == fuPlace.hotelId &&
                Objects.equals(name, fuPlace.name) &&
                Objects.equals(content, fuPlace.content) &&
                Objects.equals(capacity, fuPlace.capacity) &&
                Objects.equals(peopleNum, fuPlace.peopleNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hotelId, content, capacity, peopleNum);
    }
}
