package Utils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subjects", schema = "school", catalog = "")
public class SubjectsEntity {
    private int idSubject;
    private String name;
    private int idTeacher;

    @Id
    @GeneratedValue
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
    @Column(name = "id_teacher", nullable = false)
    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectsEntity that = (SubjectsEntity) o;
        return idSubject == that.idSubject &&
                idTeacher == that.idTeacher &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSubject, name, idTeacher);
    }
}
