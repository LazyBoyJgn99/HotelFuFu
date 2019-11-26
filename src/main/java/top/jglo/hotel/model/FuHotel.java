package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_hotel", schema = "HotelFuFu", catalog = "")
public class FuHotel {
    private int id;
    private String address;
    private String ip;
    private String star;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "star")
    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHotel fuHotel = (FuHotel) o;
        return id == fuHotel.id &&
                Objects.equals(address, fuHotel.address) &&
                Objects.equals(ip, fuHotel.ip) &&
                Objects.equals(star, fuHotel.star) &&
                Objects.equals(name, fuHotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, ip, star, name);
    }
}
