package Utils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notes", schema = "school", catalog = "")
public class NotesEntity {
    private int id;
    private int idMark;
    private int idStudent;
    private int idSubject;
    private int idTeacher;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_mark", nullable = false)
    public int getIdMark() {
        return idMark;
    }

    public void setIdMark(int idMark) {
        this.idMark = idMark;
    }

    @Basic
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
        NotesEntity that = (NotesEntity) o;
        return id == that.id &&
                idMark == that.idMark &&
                idStudent == that.idStudent &&
                idSubject == that.idSubject &&
                idTeacher == that.idTeacher;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idMark, idStudent, idSubject, idTeacher);
    }
}
