package Student;

import Application.Attention;
import Application.AttentionDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class StudentAttentionsController {

    @FXML
    private TableView<Attention> student_attentions_table_view;

    @FXML
    private TableColumn<Attention, String> student_attention_column;

    @FXML
    private Button student_attentions_button_refresh;

    @FXML
    private TableColumn<Attention, String> student_attention_date_column;


    @FXML
    void studentRefreshAttentions(){
       ObservableList<Attention> attentions = AttentionDAO.searchAttentions();
       student_attentions_table_view.setItems(attentions);
    }


    @FXML
    public void initialize(){
        studentRefreshAttentions();

        student_attention_column.setCellValueFactory(cellData->cellData.getValue().attentionProperty());
        student_attention_date_column.setCellValueFactory(cellData->cellData.getValue().dateProperty());

    }

}