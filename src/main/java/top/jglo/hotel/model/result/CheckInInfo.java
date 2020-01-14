package top.jglo.hotel.model.result;

import top.jglo.hotel.model.FuRole;
import top.jglo.hotel.model.FuWorker;

import java.util.List;

/**
 * @author jingening
 */
public class CheckInInfo {

    private int houseClassId;

    private int houseId;

    private List<Integer> userIdList;

    private String endTime;

    public int getHouseClassId() {
        return houseClassId;
    }

    public void setHouseClassId(int houseClassId) {
        this.houseClassId = houseClassId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }
}