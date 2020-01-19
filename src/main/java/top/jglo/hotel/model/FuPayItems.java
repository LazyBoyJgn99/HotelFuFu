package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "fu_pay_items", schema = "HotelFuFu", catalog = "")
public class FuPayItems implements Serializable {
    private int id;
    private String payName;
    private int typeId;
    private int price;

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
    @Column(name = "pay_name")
    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    @Basic
    @Column(name = "type_id")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuPayItems that = (FuPayItems) o;
        return id == that.id &&
                typeId == that.typeId &&
                price == that.price &&
                Objects.equals(payName, that.payName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payName, typeId, price);
    }
}
