package Utils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teachers", schema = "school", catalog = "")
public class TeachersEntity {
    private int idTeacher;
    private String name;
    private String surename;
    private int loginId;

    @Id
    @GeneratedValue
    @Column(name = "id_teacher", nullable = false)
    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surename", nullable = false, length = 20)
    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    @Basic
    @Column(name = "login_id", nullable = false)
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachersEntity that = (TeachersEntity) o;
        return idTeacher == that.idTeacher &&
                loginId == that.loginId &&
                Objects.equals(name, that.name) &&
                Objects.equals(surename, that.surename);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTeacher, name, surename, loginId);
    }
}
