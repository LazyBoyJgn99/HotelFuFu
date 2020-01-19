package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fu_house_open", schema = "HotelFuFu", catalog = "")
public class FuHouseOpen implements Serializable {
    private int id;
    private int userId;
    private int houseId;
    private int status;
    private String startTime;
    private Integer lastTime;
    private String endTime;
    private String commitTime;
    private Integer workerId;
    private List<FuUser> user;
    @OneToMany
    @JoinColumn(name = "id",referencedColumnName = "user_id")
    public List<FuUser> getUser() {
        return user;
    }
    public void setUser(List<FuUser> user) {
        this.user = user;
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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "house_id")
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "last_time")
    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "commit_time")
    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    @Basic
    @Column(name = "worker_id")
    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHouseOpen that = (FuHouseOpen) o;
        return id == that.id &&
                userId == that.userId &&
                houseId == that.houseId &&
                status == that.status &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(commitTime, that.commitTime) &&
                Objects.equals(workerId, that.workerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, houseId, status, startTime, lastTime, endTime, commitTime, workerId);
    }
}
