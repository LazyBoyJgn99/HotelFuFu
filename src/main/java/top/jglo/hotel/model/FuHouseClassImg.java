package top.jglo.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "fu_house_class_img", schema = "HotelFuFu", catalog = "")
public class FuHouseClassImg implements Serializable {
    private int id;
    private String src;
    private int classId;

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
    @Column(name = "src")
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Basic
    @Column(name = "class_id")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuHouseClassImg that = (FuHouseClassImg) o;
        return id == that.id &&
                classId == that.classId &&
                Objects.equals(src, that.src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, src, classId);
    }
}
