package Application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private IntegerProperty student_id;
    private StringProperty name;
    private StringProperty surename;
    private IntegerProperty login_id;
    private IntegerProperty subject_id;
    private StringProperty email;

    public Student(){

        this.student_id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.surename = new SimpleStringProperty();
        this.login_id = new SimpleIntegerProperty();
        this.subject_id = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurename() {
        return surename.get();
    }

    public StringProperty surenameProperty() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename.set(surename);
    }

    public int getLogin_id() {
        return login_id.get();
    }

    public IntegerProperty login_idProperty() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id.set(login_id);
    }

    public int getSubject_id() {
        return subject_id.get();
    }

    public IntegerProperty subject_idProperty() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id.set(subject_id);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
