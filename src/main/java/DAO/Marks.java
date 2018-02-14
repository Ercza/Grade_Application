package DAO;

import javafx.beans.property.*;

public class Marks {

    private IntegerProperty id_mark;
    private StringProperty mark;

    public Marks() {
        this.id_mark = new SimpleIntegerProperty();
        this.mark = new SimpleStringProperty();
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

    public String getMark() {
        return mark.get();
    }

    public StringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }

}
