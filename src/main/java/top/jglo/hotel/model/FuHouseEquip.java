package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_house_equip", schema = "HotelFuFu", catalog = "")
public class FuHouseEquip {
    private int id;
    private String name;
    private String entityId;
    private Integer houseId;
    private String mac;
    private String key;
    private Integer type;
    private Integer hotelId;
    private String host;
    private Integer serverId;

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
    @Column(name = "entity_id")
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Basic
    @Column(name = "house_id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Basic
    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "hotel_id")
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Basic
    @Column(name = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Basic
    @Column(name = "server_id")
    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHouseEquip that = (FuHouseEquip) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(entityId, that.entityId) &&
                Objects.equals(houseId, that.houseId) &&
                Objects.equals(mac, that.mac) &&
                Objects.equals(key, that.key) &&
                Objects.equals(type, that.type) &&
                Objects.equals(hotelId, that.hotelId) &&
                Objects.equals(host, that.host) &&
                Objects.equals(serverId, that.serverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, entityId, houseId, mac, key, type, hotelId, host, serverId);
    }
}
