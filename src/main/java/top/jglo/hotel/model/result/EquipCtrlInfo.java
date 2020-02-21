package top.jglo.hotel.model.result;

/**
 * @author jingening
 */
public class EquipCtrlInfo {

    private String url;

    private String entityId;

    private String bearer;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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
                ", entityId='" + entityId + '\'' +
                ", bearer='" + bearer + '\'' +
                '}';
    }
}