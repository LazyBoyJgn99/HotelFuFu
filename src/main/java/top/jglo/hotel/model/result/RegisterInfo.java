package top.jglo.hotel.model.result;

import top.jglo.hotel.model.FuRegister;
import java.util.List;


public class RegisterInfo  {
    private FuRegister register;
    private List<Integer> userIdList;

    public FuRegister getRegister() {
        return register;
    }

    public void setRegister(FuRegister register) {
        this.register = register;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }
}
