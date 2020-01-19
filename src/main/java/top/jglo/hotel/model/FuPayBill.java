package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fu_pay_bill", schema = "HotelFuFu", catalog = "")
public class FuPayBill implements Serializable {
    private int id;
    private String time;
    private int collectorId;
    private int checkerId;
    private int sumPay;
    private Integer status;
    private String payType;
    private Integer free;

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
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "collector_id")
    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
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
                collectorId == fuPayBill.collectorId &&
                checkerId == fuPayBill.checkerId &&
                sumPay == fuPayBill.sumPay &&
                Objects.equals(time, fuPayBill.time) &&
                Objects.equals(status, fuPayBill.status) &&
                Objects.equals(payType, fuPayBill.payType) &&
                Objects.equals(free, fuPayBill.free);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, collectorId, checkerId, sumPay, status, payType, free);
    }
}
