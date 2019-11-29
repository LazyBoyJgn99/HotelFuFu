package top.jglo.hotel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fu_engine", schema = "HotelFuFu", catalog = "")
public class FuEngine {
    private int id;
    private String name;
    private String src;
    private String version;
    private Integer nextId;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "src")
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuEngine fuEngine = (FuEngine) o;
        return id == fuEngine.id &&
                Objects.equals(name, fuEngine.name) &&
                Objects.equals(src, fuEngine.src) &&
                Objects.equals(version, fuEngine.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, src, version);
    }

    @Basic
    @Column(name = "next_id")
    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }
}
