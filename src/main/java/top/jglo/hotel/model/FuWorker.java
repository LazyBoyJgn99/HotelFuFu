package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_worker", schema = "HotelFuFu", catalog = "")
public class FuWorker {
    private int id;
    private String name;
    private String pwd;
    private String workNum;
    private Integer hotelId;
    private String phone;
    private String username;

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
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "work_num")
    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuWorker fuWorker = (FuWorker) o;
        return id == fuWorker.id &&
                Objects.equals(name, fuWorker.name) &&
                Objects.equals(pwd, fuWorker.pwd) &&
                Objects.equals(workNum, fuWorker.workNum) &&
                Objects.equals(hotelId, fuWorker.hotelId) &&
                Objects.equals(phone, fuWorker.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  pwd, workNum, hotelId, phone,username);
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
