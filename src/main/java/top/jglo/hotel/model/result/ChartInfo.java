package top.jglo.hotel.model.result;

import java.util.List;

/**
 * @author jingening
 */
public class ChartInfo {

    private String key;

    private String value;

    public ChartInfo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}