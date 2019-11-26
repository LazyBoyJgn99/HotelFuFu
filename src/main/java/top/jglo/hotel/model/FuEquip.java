package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_equip", schema = "HotelFuFu", catalog = "")
public class FuEquip {
    private int id;
    private String name;
    private String equipUid;
    private String type;
    private Integer houseId;
    private Integer placeId;

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
    @Column(name = "equip_uid")
    public String getEquipUid() {
        return equipUid;
    }

    public void setEquipUid(String equipUid) {
        this.equipUid = equipUid;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "place_id")
    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuEquip fuEquip = (FuEquip) o;
        return id == fuEquip.id &&
                Objects.equals(name, fuEquip.name) &&
                Objects.equals(equipUid, fuEquip.equipUid) &&
                Objects.equals(type, fuEquip.type) &&
                Objects.equals(houseId, fuEquip.houseId) &&
                Objects.equals(placeId, fuEquip.placeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, equipUid, type, houseId, placeId);
    }
}
