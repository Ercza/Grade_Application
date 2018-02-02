package Teacher;

import Application.Absence;
import Application.AbsenceDAO;
import Utils.DialogUtils;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class TeacherAbsenceController {

    @FXML
    private TableView<Absence> teacher_absence_table_view;

    @FXML
    private TableColumn<Absence, Integer> teacher_absence_id_column;

    @FXML
    private TableColumn<Absence, String> teacher_absence_name_column;

    @FXML
    private TableColumn<Absence, String> teacher_absence_surename_column;

    @FXML
    private TableColumn<Absence, String> teacher_absence_date_column;

    @FXML
    private JFXTimePicker teacher_absence_time_picker;

    @FXML
    private JFXDatePicker teacher_absence_date_picker;

    @FXML
    private TextField teacher_absence_name_text_field;

    @FXML
    private TextField teacher_absence_surename_text_field;

    @FXML
    private TextField id_field;

    @FXML
    private Button teacher_absence_add_button;

    @FXML
    private Button teacher_absence_delete_button;

    @FXML
    private Button teacher_absence_update_button;


    @FXML
    void addAbsence() {
        if (teacher_absence_name_text_field.getText().matches("[a-zA-Z]+")&& teacher_absence_surename_text_field.getText().matches("[a-zA-Z]+")) {
            if (!teacher_absence_time_picker.getValue().toString().equals(null) || !teacher_absence_date_picker.getValue().toString().equals(null) || !teacher_absence_name_text_field.getText().isEmpty() || !teacher_absence_time_picker.getValue().toString().isEmpty()) {
                if (DialogUtils.addDialog().get() == ButtonType.OK) {
                    AbsenceDAO.insertAbsence(teacher_absence_name_text_field.getText(), teacher_absence_surename_text_field.getText(), teacher_absence_time_picker.getValue().toString() + " " + teacher_absence_date_picker.getValue().toString());
                    searchAbsence();
                } else {
                    DialogUtils.informationDialog("Prosze wypełnić wszystkie formularze");
                }
            } else {
                DialogUtils.informationDialog("Prosze wypełnić wszystkie formularze");
            }
        }else{
            DialogUtils.informationDialog("Proszę podać prawidłowe Imię oraz Nazwisko");
        }
    }

    @FXML
    void searchAbsence() {
        teacher_absence_name_text_field.clear();
        teacher_absence_surename_text_field.clear();
        ObservableList<Absence> absences = AbsenceDAO.searchAbsences();
        teacher_absence_table_view.setItems(absences);
    }


    @FXML
    void deleteAbsence() {
        if(id_field.getText().matches("[0-9]+")) {
            if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(id_field.getText());
                AbsenceDAO.deleteAbsenceWithId(x);
                searchAbsence();
            }
        }else{
            DialogUtils.informationDialog("Proszę o podanie prawidłowego ID");
        }
    }

    @FXML
    void updateAbsence() {
        if(id_field.getText().matches("[0-9]+")) {
            if (DialogUtils.updateDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(id_field.getText());
                AbsenceDAO.updateAbsence(x, teacher_absence_name_text_field.getText(), teacher_absence_surename_text_field.getText(), teacher_absence_time_picker.getValue().toString() + " " + teacher_absence_date_picker.getValue().toString());
                searchAbsence();
            }
        }else{
            DialogUtils.informationDialog("Proszę o podanie prawidłowego ID");
        }
    }

    @FXML
    public void initialize() {
        searchAbsence();


        teacher_absence_id_column.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        teacher_absence_name_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        teacher_absence_surename_column.setCellValueFactory(cellData -> cellData.getValue().surenameProperty());
        teacher_absence_date_column.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        teacher_absence_table_view.setRowFactory(tableView2 -> {
            final TableRow<Absence> row = new TableRow<>();
            row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                final int index = row.getIndex();
                Absence clickedRow = row.getItem();
                if (!row.isEmpty()) { // tutaj pobieranie danych z tabeli do pol
                    id_field.setText(String.valueOf(clickedRow.getId()));
                    teacher_absence_name_text_field.setText(clickedRow.getName());
                    teacher_absence_surename_text_field.setText(clickedRow.getSurename());
                }
                if (index >= 0 && index < teacher_absence_table_view.getItems().size() && teacher_absence_table_view.getSelectionModel().isSelected(index)) {
                    teacher_absence_table_view.getSelectionModel().clearSelection();
                    id_field.clear();
                    teacher_absence_name_text_field.clear();
                    teacher_absence_surename_text_field.clear();
                    event.consume();
                }
            });
            return row;
        });
    }

}
