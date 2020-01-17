package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fu_house_class", schema = "HotelFuFu", catalog = "")
public class FuHouseClass implements Serializable {
    private int id;
    private String name;
    private String content;
    private int hotelId;
    private int bedCapacity;
    private int userCapacity;
    private List<FuHouse> houseList;
    private List<FuHouseClassImg> houseClassImgList;
    private List<FuService> services;
    private List<FuHouseClassPrice> houseClassPrices;

    @ManyToMany
    @JoinTable(
            name="fu_house_class_service",
//        joinColumns = {@JoinColumn(name="ITEM_ID",referencedColumnName="I_ID")},
            joinColumns = {@JoinColumn(name="house_class_id",referencedColumnName="id")},
//        inverseJoinColumns= {@JoinColumn(name="CATEGORY_ID", referencedColumnName="C_ID")})
            inverseJoinColumns= {@JoinColumn(name="service_id",referencedColumnName="id")}
    )
    public List<FuService> getServices() {
        return services;
    }

    public void setServices(List<FuService> services) {
        this.services = services;
    }
    @OneToMany
    @JoinColumn(name = "class_id",referencedColumnName = "id")
    public List<FuHouseClassPrice> getHouseClassPrices() {
        return houseClassPrices;
    }

    public void setHouseClassPrices(List<FuHouseClassPrice> houseClassPrices) {
        this.houseClassPrices = houseClassPrices;
    }

    @OneToMany
    @JoinColumn(name = "class_id",referencedColumnName = "id")
    public List<FuHouseClassImg> getHouseClassImgList() {
        return houseClassImgList;
    }

    public void setHouseClassImgList(List<FuHouseClassImg> houseClassImgList) {
        this.houseClassImgList = houseClassImgList;
    }

    @OneToMany
    @JoinColumn(name = "class_id",referencedColumnName = "id")
    public List<FuHouse> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<FuHouse> houseList) {
        this.houseList = houseList;
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

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
