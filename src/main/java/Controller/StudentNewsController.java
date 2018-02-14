package Controller;

import DAO.News;
import DAO.NewsDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentNewsController {

    @FXML
    private TableView<News> student_news_table_view;

    @FXML
    private TableColumn<News, String> student_news_description_column;

    @FXML
    private Button student_news_button_refresh;

    @FXML
    void studentRefreshNews(){
        ObservableList<News> news = NewsDAO.searchNews();
        student_news_table_view.setItems(news);
    }

    public void initialize(){
        studentRefreshNews();
        student_news_description_column.setCellValueFactory(cellData->cellData.getValue().descriptionProperty());
    }

}