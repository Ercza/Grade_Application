package Application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Absence {

    private StringProperty name;
    private StringProperty surename;
    private StringProperty date;
    private IntegerProperty id;

    public Absence(String name, String surename, String date, Integer id){

        this.name = new SimpleStringProperty(name);
        this.surename =  new SimpleStringProperty(surename);
        this.date = new SimpleStringProperty(date);
        this.id = new SimpleIntegerProperty(id);
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

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
