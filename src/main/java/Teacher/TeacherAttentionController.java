package Teacher;

import Application.Attention;
import Application.AttentionDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Date;

public class TeacherAttentionController {

    @FXML
    private TableView<Attention> teacher_attention_table_view;

    @FXML
    private TableColumn<Attention, Integer> teacher_attention_id_column;

    @FXML
    private TableColumn<Attention, String> teacher_attention_column;

    @FXML
    private TableColumn<Attention, String> teacher_attention_date_column;

    @FXML
    private Button teacher_attention_button_add;

    @FXML
    private Button teacher_attention_button_udpate;

    @FXML
    private Button teacher_attention_button_delete;

    @FXML
    private DatePicker teacher_attention_date_picker;

    @FXML
    private TextField teacher_attention_id_text_field;

    @FXML
    private TextArea teacher_attention_text_area;

    @FXML
    void teacherAttentionAdd(ActionEvent event){
        try {
            AttentionDAO.insertAttention(teacher_attention_text_area.getText(),teacher_attention_date_picker.getValue().toString());
            searchAttentions();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void searchAttentions() throws ClassNotFoundException {
        try {
            ObservableList<Attention> attentionData = AttentionDAO.searchAttentions();
            populateAttentions(attentionData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void populateAttentions(ObservableList<Attention> attentionData) throws ClassNotFoundException {
        teacher_attention_table_view.setItems(attentionData);
    }

    @FXML
    void teacherAttentionDelete(ActionEvent event) throws ClassNotFoundException {
        AttentionDAO.deleteAttentionWithId(teacher_attention_id_text_field.getText());
        searchAttentions();
    }

    @FXML
    void teacherAttentionUpdate(ActionEvent event) throws ClassNotFoundException {
        AttentionDAO.updateAttention(teacher_attention_id_text_field.getText(), teacher_attention_text_area.getText(), teacher_attention_date_picker.getValue().toString());
        searchAttentions();

    }

    @FXML
    public void initialize() throws ClassNotFoundException {

        searchAttentions();

        teacher_attention_id_column.setCellValueFactory(cellData->cellData.getValue().student_idProperty().asObject());
        teacher_attention_column.setCellValueFactory(cellData->cellData.getValue().attentionProperty());
        teacher_attention_date_column.setCellValueFactory(cellData->cellData.getValue().dateProperty());
    }


}
