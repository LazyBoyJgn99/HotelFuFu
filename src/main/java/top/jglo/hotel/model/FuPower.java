package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "fu_power", schema = "HotelFuFu", catalog = "")
public class FuPower implements Serializable {
    private int id;
    private String name;
    private Integer hotelId;

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
        FuPower fuPower = (FuPower) o;
        return id == fuPower.id &&
                Objects.equals(name, fuPower.name) &&
                Objects.equals(hotelId, fuPower.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hotelId);
    }
}
