package Utils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "absences", schema = "school", catalog = "")
public class AbsencesEntity {
    private String name;
    private String surename;
    private String date;
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surename", nullable = false, length = -1)
    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    @Basic
    @Column(name = "date", nullable = false, length = 20)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id

    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsencesEntity that = (AbsencesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surename, that.surename) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surename, date, id);
    }
}
