package Application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class News {

    private IntegerProperty news_id;
    private StringProperty description;

    public News() {

        this.news_id = new SimpleIntegerProperty();
        this.description = new SimpleStringProperty();
    }

    public int getNews_id() {
        return news_id.get();
    }

    public IntegerProperty news_idProperty() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id.set(news_id);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
