package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_pay_single", schema = "HotelFuFu", catalog = "")
public class FuPaySingle {
    private int id;
    private int userId;
    private int payItemsId;
    private int amount;
    private int sum;
    private Integer manyPayId;
    private Integer status;

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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "pay_items_id")
    public int getPayItemsId() {
        return payItemsId;
    }

    public void setPayItemsId(int payItemsId) {
        this.payItemsId = payItemsId;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "sum")
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "many_pay_id")
    public Integer getManyPayId() {
        return manyPayId;
    }

    public void setManyPayId(Integer manyPayId) {
        this.manyPayId = manyPayId;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuPaySingle that = (FuPaySingle) o;
        return id == that.id &&
                userId == that.userId &&
                payItemsId == that.payItemsId &&
                amount == that.amount &&
                sum == that.sum &&
                Objects.equals(manyPayId, that.manyPayId) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, payItemsId, amount, sum, manyPayId, status);
    }
}
