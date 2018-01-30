package Utils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students", schema = "school", catalog = "")
public class StudentsEntity {
    private int idStudent;
    private int idSubject;
    private String name;
    private String surename;
    private int loginId;

    @Id
    @GeneratedValue
    @Column(name = "id_student", nullable = false)
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Basic
    @Column(name = "id_subject", nullable = false)
    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
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
        StudentsEntity that = (StudentsEntity) o;
        return idStudent == that.idStudent &&
                idSubject == that.idSubject &&
                loginId == that.loginId &&
                Objects.equals(name, that.name) &&
                Objects.equals(surename, that.surename);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStudent, idSubject, name, surename, loginId);
    }

}
