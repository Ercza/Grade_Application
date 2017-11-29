package Application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marks {

    private IntegerProperty student_id;
    private StringProperty subject_id;
    private IntegerProperty mark;


    public Marks() {
        this.student_id = new SimpleIntegerProperty();
        this.subject_id = new SimpleStringProperty();
        this.mark = new SimpleIntegerProperty();
    }

    public int getStudent_id() {
        return student_id.get();
    }

    public IntegerProperty student_idProperty() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id.set(student_id);
    }

    public String getSubject_id() {
        return subject_id.get();
    }

    public StringProperty subject_idProperty() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id.set(subject_id);
    }

    public int getMark() {
        return mark.get();
    }

    public IntegerProperty markProperty() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark.set(mark);
    }
}
