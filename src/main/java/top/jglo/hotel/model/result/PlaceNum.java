package top.jglo.hotel.model.result;

import top.jglo.hotel.model.FuPlace;

/**
 * @author jingening
 */
public class PlaceNum {

    private FuPlace place;
    private String num;

    public PlaceNum(FuPlace place, String num) {
        this.place = place;
        this.num = num;
    }

    public FuPlace getPlace() {
        return place;
    }

    public void setPlace(FuPlace place) {
        this.place = place;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}