package Teacher;


import Application.News;
import Application.NewsDAO;
import Utils.DialogUtils;
import Utils.NewsEntity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Optional;

public class TeacherNewsController {

    @FXML
    private TableView<News> teacher_news_table_view;

    @FXML
    private TableColumn<News, Integer> id_column;

    @FXML
    private TableColumn<News, String> teacher_news_description_column;

    @FXML
    private TextField id_field;

    @FXML
    private TextArea news_area;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;


    @FXML
    private Button teacher_news_button_refresh;

    @FXML
    void teacherRefreshNews() {
        ObservableList<News> news = NewsDAO.searchNews();
        teacher_news_table_view.setItems(news);
    }

    @FXML
    void addNews() {

        if (DialogUtils.addDialog().get() == ButtonType.OK) {
            NewsDAO.insertNews(news_area.getText());
            teacherRefreshNews();
        }
    }

    @FXML
    void deleteNews() {

        if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(id_field.getText());
            NewsDAO.deleteNewsWithId(x);
            teacherRefreshNews();
        }
    }

    @FXML
    void updateNews() {

        if (DialogUtils.updateDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(id_field.getText());
            NewsDAO.updateNews(x, news_area.getText());
            teacherRefreshNews();
        }
    }


    public void initialize() {
        teacherRefreshNews();
        id_column.setCellValueFactory(cellData -> cellData.getValue().news_idProperty().asObject());
        teacher_news_description_column.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
    }

}