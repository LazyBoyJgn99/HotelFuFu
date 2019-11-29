package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_service", schema = "HotelFuFu", catalog = "")
public class FuService {
    private int id;
    private String name;
    private String content;
    private int hotelId;
    private int price;
    private int show;
    private int remind;
    private Integer time;
    private Integer placeId;

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
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "show")
    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    @Basic
    @Column(name = "remind")
    public int getRemind() {
        return remind;
    }

    public void setRemind(int remind) {
        this.remind = remind;
    }

    @Basic
    @Column(name = "time")
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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
        FuService fuService = (FuService) o;
        return id == fuService.id &&
                hotelId == fuService.hotelId &&
                price == fuService.price &&
                show == fuService.show &&
                remind == fuService.remind &&
                Objects.equals(name, fuService.name) &&
                Objects.equals(content, fuService.content) &&
                Objects.equals(time, fuService.time) &&
                Objects.equals(placeId, fuService.placeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, hotelId, price, show, remind, time, placeId);
    }
}
