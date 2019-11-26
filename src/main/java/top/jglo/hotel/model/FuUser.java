package top.jglo.hotel.model;

import groovy.lang.Lazy;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "fu_user", schema = "HotelFuFu", catalog = "")
public class FuUser {
    private int id;
    private String cardId;
    private String faceAddress;
    private byte[] faceDetail;
    private String sex;
    private String phone;
    private int vipLevel;
    private int balance;

    @Id
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
}
