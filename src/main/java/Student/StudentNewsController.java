package Student;

import Application.News;
import Application.NewsDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class StudentNewsController {

    @FXML
    private TableView<News> student_news_table_view;

    @FXML
    private TableColumn<News, String> student_news_description_column;

    @FXML
    private Button student_news_button_refresh;

    @FXML
    void studentRefreshNews() throws ClassNotFoundException {
        try {
            ObservableList<News> news = NewsDAO.searchNews();
            populateNews(news);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void populateNews(ObservableList<News> news) {
        student_news_table_view.setItems(news);
    }

    public void initialize() throws ClassNotFoundException {
        studentRefreshNews();
        student_news_description_column.setCellValueFactory(cellData->cellData.getValue().descriptionProperty());
    }

}