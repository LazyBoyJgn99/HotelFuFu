package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "fu_user", schema = "HotelFuFu", catalog = "")
public class FuUser implements Serializable {
    private int id;
    private String cardId;
    private String faceAddress;
    private byte[] faceDetail;
    private String sex;
    private String phone;
    private int vipLevel;
    private int balance;
    private String name;

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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
    @Column(name = "card_id")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "face_address")
    public String getFaceAddress() {
        return faceAddress;
    }

    public void setFaceAddress(String faceAddress) {
        this.faceAddress = faceAddress;
    }

    @Lob
    @Basic
    @Column(name = "face_detail")
    public byte[] getFaceDetail() {
        return faceDetail;
    }

    public void setFaceDetail(byte[] faceDetail) {
        this.faceDetail = faceDetail;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "vip_level")
    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    @Basic
    @Column(name = "balance")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "FuUser{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", faceAddress='" + faceAddress + '\'' +
                ", faceDetail=" + Arrays.toString(faceDetail) +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", vipLevel=" + vipLevel +
                ", balance=" + balance +
                '}';
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
        FuUser fuUser = (FuUser) o;
        return id == fuUser.id &&
                vipLevel == fuUser.vipLevel &&
                balance == fuUser.balance &&
                Objects.equals(cardId, fuUser.cardId) &&
                Objects.equals(faceAddress, fuUser.faceAddress) &&
                Arrays.equals(faceDetail, fuUser.faceDetail) &&
                Objects.equals(sex, fuUser.sex) &&
                Objects.equals(phone, fuUser.phone) &&
                Objects.equals(name, fuUser.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, cardId, faceAddress, sex, phone, vipLevel, balance);
        result = 31 * result + Arrays.hashCode(faceDetail);
        return result;
    }
}
