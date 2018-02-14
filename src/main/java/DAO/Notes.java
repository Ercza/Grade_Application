package DAO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Notes {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty surename;
    private StringProperty subject;
    private IntegerProperty note;

    public Notes(int id, String name, String surename, String subject, int note) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surename = new SimpleStringProperty(surename);
        this.subject = new SimpleStringProperty(subject);
        this.note = new SimpleIntegerProperty(note);
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

    public String getSubject() {
        return subject.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public int getNote() {
        return note.get();
    }

    public IntegerProperty noteProperty() {
        return note;
    }

    public void setNote(int note) {
        this.note.set(note);
    }
}
