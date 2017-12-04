package Teacher;


import Application.News;
import Application.NewsDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class TeacherNewsController {

    @FXML
    private TableView<News> teacher_news_table_view;

    @FXML
    private TableColumn<News, String> teacher_news_description_column;

    @FXML
    private Button teacher_news_button_refresh;

    @FXML
    void teacherRefreshNews() throws ClassNotFoundException {
        try {
            ObservableList<News> news = NewsDAO.searchNews();
            populateNews(news);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void populateNews(ObservableList<News> news) {
        teacher_news_table_view.setItems(news);
    }

    public void initialize() throws ClassNotFoundException {
        teacherRefreshNews();
        teacher_news_description_column.setCellValueFactory(cellData->cellData.getValue().descriptionProperty());
    }

}