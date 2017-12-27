package Application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attention {

    private IntegerProperty student_id;
    private StringProperty attention;
    private StringProperty date;

    public Attention(int student_id, String attention, String date) {

        this.student_id = new SimpleIntegerProperty(student_id);
        this.attention = new SimpleStringProperty(attention);
        this.date = new SimpleStringProperty(date);
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

    public String getAttention() {
        return attention.get();
    }

    public StringProperty attentionProperty() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention.set(attention);
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
