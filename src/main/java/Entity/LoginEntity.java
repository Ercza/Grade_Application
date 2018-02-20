package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login", schema = "school", catalog = "")
public class LoginEntity {
    private int id;
    private String username;
    private String password;
    private String partof;

    private StudentsEntity studentID;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 16)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "partof", nullable = false)
    public String getPartof() {
        return partof;
    }

    public void setPartof(String partof) {
        this.partof = partof;
    }

    @OneToOne
    @JoinColumn(name = "student_id")
    public StudentsEntity getStudentID() {
        return studentID;
    }

    public void setStudentID(StudentsEntity studentID) {
        this.studentID = studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginEntity that = (LoginEntity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(partof, that.partof);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, partof);
    }
}
