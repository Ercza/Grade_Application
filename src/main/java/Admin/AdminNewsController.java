package Admin;

import Application.News;
import Application.NewsDAO;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.sql.SQLException;


public class AdminNewsController {

    @FXML
    private TableView<News> admin_news_tableview;

    @FXML
    private TableColumn<News, Integer> admin_news_id_column;

    @FXML
    private TableColumn<News, String> admin_news_description_column;

    @FXML
    private Button admin_news_button_add_news;

    @FXML
    private Button admin_news_button_update;

    @FXML
    private Button admin_news_button_delete;

    @FXML
    private Button admin_news_button_refresh;

    @FXML
    private TextArea admin_news_text_area;

    @FXML
    private JFXTextField admin_text_field_id;

    @FXML
    void adminAddNews(ActionEvent event) {
        NewsDAO.insertNews(admin_news_text_area.getText());
        adminRefreshNews();
    }

    @FXML
    void adminRefreshNews() {
        ObservableList<News> news = NewsDAO.searchNews();
        admin_news_tableview.setItems(news);
    }

    @FXML
    void adminUpdateNews(ActionEvent event) {
        int x = Integer.parseInt(admin_text_field_id.getText());
        NewsDAO.updateNews(x, admin_news_text_area.getText());
        adminRefreshNews();
    }

    @FXML
    void adminDeleteNews(ActionEvent event) {
        int x = Integer.parseInt(admin_text_field_id.getText());
        NewsDAO.deleteNewsWithId(x);
        adminRefreshNews();
    }

    public void initialize() {
        adminRefreshNews();

        admin_news_id_column.setCellValueFactory(cellData -> cellData.getValue().news_idProperty().asObject());
        admin_news_description_column.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

    }

}

