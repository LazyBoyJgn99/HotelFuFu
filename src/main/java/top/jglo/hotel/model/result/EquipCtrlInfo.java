package top.jglo.hotel.model.result;

/**
 * @author jingening
 */
public class EquipCtrlInfo {

    private String url;

    private String entity_id;

    private String bearer;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    @Override
    public String toString() {
        return "EquipCtrlInfo{" +
                "url='" + url + '\'' +
                ", entity_id='" + entity_id + '\'' +
                ", bearer='" + bearer + '\'' +
                '}';
    }
}