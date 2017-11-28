package Application;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    //Declare Employees Table Columns
    private IntegerProperty person_id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty partof;
    private StringProperty email;

    //Contructor
    public Person() {
        this.person_id = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.partof = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
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
