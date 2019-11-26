package top.jglo.hotel.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fu_pay_bill", schema = "HotelFuFu", catalog = "")
public class FuPayBill {
    private int id;
    private Timestamp time;
    private int collecterId;
    private int checkerId;
    private int sumPay;
    private Integer status;
    private String payType;
    private Integer free;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "collecter_id")
    public int getCollecterId() {
        return collecterId;
    }

    public void setCollecterId(int collecterId) {
        this.collecterId = collecterId;
    }

    @Basic
    @Column(name = "checker_id")
    public int getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(int checkerId) {
        this.checkerId = checkerId;
    }

    @Basic
    @Column(name = "sum_pay")
    public int getSumPay() {
        return sumPay;
    }

    public void setSumPay(int sumPay) {
        this.sumPay = sumPay;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "pay_type")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "free")
    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuPayBill fuPayBill = (FuPayBill) o;
        return id == fuPayBill.id &&
                collecterId == fuPayBill.collecterId &&
                checkerId == fuPayBill.checkerId &&
                sumPay == fuPayBill.sumPay &&
                Objects.equals(time, fuPayBill.time) &&
                Objects.equals(status, fuPayBill.status) &&
                Objects.equals(payType, fuPayBill.payType) &&
                Objects.equals(free, fuPayBill.free);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, collecterId, checkerId, sumPay, status, payType, free);
    }
}
