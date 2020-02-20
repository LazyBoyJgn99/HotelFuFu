package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_hotel_server", schema = "HotelFuFu", catalog = "")
public class FuHotelServer {
    private int id;
    private String ip;
    private Integer port;
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
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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
        FuHotelServer that = (FuHotelServer) o;
        return id == that.id &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(port, that.port) &&
                Objects.equals(hotelId, that.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ip, port, hotelId);
    }
}
