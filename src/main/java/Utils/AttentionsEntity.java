package Utils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "attentions", schema = "school", catalog = "")
public class AttentionsEntity {
    private int studentId;
    private String attention;
    private String date;

    @Id
    @Column(name = "student_id", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "attention", nullable = false, length = 1000)
    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    @Basic
    @Column(name = "date", nullable = false, length = 20)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttentionsEntity that = (AttentionsEntity) o;
        return studentId == that.studentId &&
                Objects.equals(attention, that.attention) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, attention, date);
    }
}
