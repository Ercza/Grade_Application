package Teacher;

import Application.Attention;
import Application.AttentionDAO;
import Utils.DialogUtils;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

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
    private JFXTimePicker teacher_attention_time_picker;

    @FXML
    private JFXDatePicker teacher_attention_date_picker;


    @FXML
    private TextField teacher_attention_id_text_field;

    @FXML
    private TextArea teacher_attention_text_area;

    @FXML
    void teacherAttentionAdd(ActionEvent event) {

        if (DialogUtils.addDialog().get() == ButtonType.OK) {

            AttentionDAO.insertAttention(teacher_attention_text_area.getText(), teacher_attention_time_picker.getValue().toString() + " " + teacher_attention_date_picker.getValue().toString());
            searchAttentions();
        }

    }

    @FXML
    void searchAttentions() {
        ObservableList<Attention> attentions = AttentionDAO.searchAttentions();
        teacher_attention_table_view.setItems(attentions);
    }


    @FXML
    void teacherAttentionDelete(ActionEvent event) {

        if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(teacher_attention_id_text_field.getText());
            AttentionDAO.deleteAttentionWithId(x);
            searchAttentions();
        }
    }

    @FXML
    void teacherAttentionUpdate(ActionEvent event) {

        if (DialogUtils.updateDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(teacher_attention_id_text_field.getText());
            AttentionDAO.updateAttention(x, teacher_attention_text_area.getText(), teacher_attention_time_picker.getValue().toString() + " " + teacher_attention_date_picker.getValue().toString());
            searchAttentions();
        }

    }

    @FXML
    public void initialize() {

        searchAttentions();

        teacher_attention_id_column.setCellValueFactory(cellData -> cellData.getValue().student_idProperty().asObject());
        teacher_attention_column.setCellValueFactory(cellData -> cellData.getValue().attentionProperty());
        teacher_attention_date_column.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    }


}
