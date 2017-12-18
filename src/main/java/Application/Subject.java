package Application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subject {

    private IntegerProperty subjectID;
    private StringProperty subjectName;
    private IntegerProperty teacherID;
    private IntegerProperty studentID;
    private IntegerProperty markID;

    public Subject(){
        this.subjectID = new SimpleIntegerProperty();
        this.subjectName = new SimpleStringProperty();
        this.teacherID =  new SimpleIntegerProperty();
        this.studentID =  new SimpleIntegerProperty();
        this.markID =  new SimpleIntegerProperty();
    }

    public int getSubjectID() {
        return subjectID.get();
    }

    public IntegerProperty subjectIDProperty() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID.set(subjectID);
    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public StringProperty subjectNameProperty() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
    }

    public int getTeacherID() {
        return teacherID.get();
    }

    public IntegerProperty teacherIDProperty() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID.set(teacherID);
    }

    public int getStudentID() {
        return studentID.get();
    }

    public IntegerProperty studentIDProperty() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID.set(studentID);
    }

    public int getMarkID() {
        return markID.get();
    }

    public IntegerProperty markIDProperty() {
        return markID;
    }

    public void setMarkID(int markID) {
        this.markID.set(markID);
    }
}
