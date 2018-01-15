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

import java.sql.SQLException;
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

        if (DialogUtils.addDialog().get() == ButtonType.OK) {
            AbsenceDAO.insertAbsence(teacher_absence_name_text_field.getText(), teacher_absence_surename_text_field.getText(), teacher_absence_time_picker.getValue().toString() + " " + teacher_absence_date_picker.getValue().toString());
            searchAbsence();
        }
    }

    @FXML
    void searchAbsence() {
        ObservableList<Absence> absences = AbsenceDAO.searchAbsences();
        teacher_absence_table_view.setItems(absences);
    }


    @FXML
    void deleteAbsence() {

        if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(id_field.getText());
            AbsenceDAO.deleteAbsenceWithId(x);
            searchAbsence();
        }
    }

    @FXML
    void updateAbsence() {

        if (DialogUtils.updateDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(id_field.getText());
            AbsenceDAO.updateAbsence(x, teacher_absence_name_text_field.getText(), teacher_absence_surename_text_field.getText(), teacher_absence_time_picker.getValue().toString() + " " + teacher_absence_date_picker.getValue().toString());
            searchAbsence();
        }
    }

    @FXML
    public void initialize() {
        searchAbsence();

        teacher_absence_id_column.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        teacher_absence_name_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        teacher_absence_surename_column.setCellValueFactory(cellData -> cellData.getValue().surenameProperty());
        teacher_absence_date_column.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    }

}
