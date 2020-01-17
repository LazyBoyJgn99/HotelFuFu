package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fu_role", schema = "HotelFuFu", catalog = "")
public class FuRole implements Serializable {
    private int id;
    private String name;
    private Integer hotelId;
    private List<FuPower> powerList;

    @ManyToMany
    @JoinTable(
        name="fu_role_power_relation",
//        joinColumns = {@JoinColumn(name="ITEM_ID",referencedColumnName="I_ID")},
        joinColumns = {@JoinColumn(name="role_id",referencedColumnName="id")},
//        inverseJoinColumns= {@JoinColumn(name="CATEGORY_ID", referencedColumnName="C_ID")})
        inverseJoinColumns= {@JoinColumn(name="power_id",referencedColumnName="id")}
    )
    public List<FuPower> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<FuPower> powerList) {
        this.powerList = powerList;
    }

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
        FuRole fuRole = (FuRole) o;
        return id == fuRole.id &&
                Objects.equals(name, fuRole.name) &&
                Objects.equals(hotelId, fuRole.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hotelId);
    }
}
