package top.jglo.hotel.model.result;

import top.jglo.hotel.model.FuRole;
import top.jglo.hotel.model.FuWorker;

/**
 * Created by gjw on 2018/9/28.
 */
public class WorkerInfo {

    private FuWorker worker;
    private FuRole role;

    public WorkerInfo(FuWorker worker, FuRole role) {
        this.worker = worker;
        this.role = role;
    }

    public FuWorker getWorker() {
        return worker;
    }

    public void setWorker(FuWorker worker) {
        this.worker = worker;
    }

    public FuRole getRole() {
        return role;
    }

    public void setRole(FuRole role) {
        this.role = role;
    }
}