package Application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

public class Join {

    private IntegerProperty id_student;
    private IntegerProperty id_teacher;
    private IntegerProperty id_subject;
    private IntegerProperty id_mark;

    public Join(Integer id_student, Integer id_teacher, Integer id_subject, Integer id_mark) {
        this.id_student = new SimpleIntegerProperty(id_student);
        this.id_teacher = new SimpleIntegerProperty(id_teacher);
        this.id_subject = new SimpleIntegerProperty(id_subject);
        this.id_mark = new SimpleIntegerProperty(id_mark);
    }

    public int getId_student() {
        return id_student.get();
    }

    public IntegerProperty id_studentProperty() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student.set(id_student);
    }

    public int getId_teacher() {
        return id_teacher.get();
    }

    public IntegerProperty id_teacherProperty() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher.set(id_teacher);
    }

    public int getId_subject() {
        return id_subject.get();
    }

    public IntegerProperty id_subjectProperty() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject.set(id_subject);
    }

    public int getId_mark() {
        return id_mark.get();
    }

    public IntegerProperty id_markProperty() {
        return id_mark;
    }

    public void setId_mark(int id_mark) {
        this.id_mark.set(id_mark);
    }
}
