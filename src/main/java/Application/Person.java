package Application;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Entity;


public class Person {

    private IntegerProperty person_id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty partof;

    public Person(int id, String username, String password, String partof) {
        this.person_id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.partof = new SimpleStringProperty(partof);
    }

    public String getPartof() {
        return partof.get();
    }

    public StringProperty partofProperty() {
        return partof;
    }

    public void setPartof(String partof) {
        this.partof.set(partof);
    }

    public int getPerson_id() {
        return person_id.get();
    }

    public IntegerProperty person_idProperty() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id.set(person_id);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
