package top.jglo.hotel.model.result;

import java.util.List;

/**
 * @author jingening
 */
public class RegisterFindInfo {

    private int houseClassId;

    private String commitStartTime;

    private String commitEndTime;

    private String liveDate;

    private int status;

    @Override
    public String toString() {
        return "RegisterFindInfo{" +
                "houseClassId=" + houseClassId +
                ", commitStartTime='" + commitStartTime + '\'' +
                ", commitEndTime='" + commitEndTime + '\'' +
                ", liveDate='" + liveDate + '\'' +
                ", status=" + status +
                '}';
    }

    public int getHouseClassId() {
        return houseClassId;
    }

    public void setHouseClassId(int houseClassId) {
        this.houseClassId = houseClassId;
    }

    public String getCommitStartTime() {
        return commitStartTime;
    }

    public void setCommitStartTime(String commitStartTime) {
        this.commitStartTime = commitStartTime;
    }

    public String getCommitEndTime() {
        return commitEndTime;
    }

    public void setCommitEndTime(String commitEndTime) {
        this.commitEndTime = commitEndTime;
    }

    public String getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(String liveDate) {
        this.liveDate = liveDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}