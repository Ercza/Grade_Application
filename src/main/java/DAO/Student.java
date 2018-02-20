package DAO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private IntegerProperty student_id;
    private StringProperty name;
    private StringProperty surename;
    private IntegerProperty login_id;


    public Student(int student_id, String name, String surename,int login_id){

        this.student_id = new SimpleIntegerProperty(student_id);
        this.name = new SimpleStringProperty(name);
        this.surename = new SimpleStringProperty(surename);
        this.login_id = new SimpleIntegerProperty(login_id);

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

}
