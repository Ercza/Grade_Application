package Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Absence {

    private StringProperty name;
    private StringProperty surename;
    private StringProperty date;

    public Absence(){

        this.name = new SimpleStringProperty();
        this.surename =  new SimpleStringProperty();
        this.date = new SimpleStringProperty();
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
}
