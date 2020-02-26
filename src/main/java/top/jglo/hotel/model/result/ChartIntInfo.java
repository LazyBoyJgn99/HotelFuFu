package top.jglo.hotel.model.result;

/**
 * @author jingening
 */
public class ChartIntInfo {

    private String key;

    private Integer value;

    public ChartIntInfo() {

    }
    public ChartIntInfo(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}