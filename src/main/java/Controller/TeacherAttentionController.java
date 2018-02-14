package Controller;

import DAO.Attention;
import DAO.AttentionDAO;
import Utils.DialogUtils;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
    private Button refresh_btn;

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
        if(!teacher_attention_text_area.getText().isEmpty()) {
            if (DialogUtils.addDialog().get() == ButtonType.OK) {

                AttentionDAO.insertAttention(teacher_attention_text_area.getText(), teacher_attention_time_picker.getValue().toString() + " " + teacher_attention_date_picker.getValue().toString());
                searchAttentions();
            }else{
                DialogUtils.informationDialog("Proszę wypełnić formularze");
            }
        }else{
         DialogUtils.informationDialog("Proszę wypełnić formularze");
        }

    }

    @FXML
    void searchAttentions() {
        ObservableList<Attention> attentions = AttentionDAO.searchAttentions();
        teacher_attention_table_view.setItems(attentions);
    }

    @FXML
    void refresh(){
        teacher_attention_id_text_field.clear();
        teacher_attention_text_area.clear();
        ObservableList<Attention> attentions = AttentionDAO.searchAttentions();
        teacher_attention_table_view.setItems(attentions);
    }


    @FXML
    void teacherAttentionDelete(ActionEvent event) {
        if(teacher_attention_id_text_field.getText().matches("[0-9]+")) {
            if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(teacher_attention_id_text_field.getText());
                AttentionDAO.deleteAttentionWithId(x);
                searchAttentions();
            }
        }else{
            DialogUtils.informationDialog("Proszę podać poprawne ID");
        }
    }

    @FXML
    void teacherAttentionUpdate(ActionEvent event) {
        if(teacher_attention_id_text_field.getText().matches("[0-9]+")) {
            if (DialogUtils.updateDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(teacher_attention_id_text_field.getText());
                AttentionDAO.updateAttention(x, teacher_attention_text_area.getText(), teacher_attention_time_picker.getValue().toString() + " " + teacher_attention_date_picker.getValue().toString());
                searchAttentions();
            }
        }else{
            DialogUtils.informationDialog("Proszę podać poprawne ID");
        }

    }

    @FXML
    public void initialize() {

        searchAttentions();

        teacher_attention_id_column.setCellValueFactory(cellData -> cellData.getValue().student_idProperty().asObject());
        teacher_attention_column.setCellValueFactory(cellData -> cellData.getValue().attentionProperty());
        teacher_attention_date_column.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        teacher_attention_table_view.setRowFactory(tableView2 -> {
            final TableRow<Attention> row = new TableRow<>();
            row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                final int index = row.getIndex();
                Attention clickedRow = row.getItem();
                if (!row.isEmpty()) { // tutaj pobieranie danych z tabeli do pol
                    teacher_attention_id_text_field.setText(String.valueOf(clickedRow.getStudent_id()));
                    teacher_attention_text_area.setText(clickedRow.getAttention());
                }
                if (index >= 0 && index < teacher_attention_table_view.getItems().size() && teacher_attention_table_view.getSelectionModel().isSelected(index)) {
                    teacher_attention_table_view.getSelectionModel().clearSelection();
                    teacher_attention_id_text_field.clear();
                    teacher_attention_text_area.clear();
                    event.consume();
                }
            });
            return row;
        });
    }


}
