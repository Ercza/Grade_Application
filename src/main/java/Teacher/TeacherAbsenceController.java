package Teacher;

import Application.Absence;
import Application.AbsenceDAO;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class TeacherAbsenceController {

    @FXML
    private TableView<Absence> teacher_absence_table_view;

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
    private Button teacher_absence_add_button;

    @FXML
    private Button teacher_absence_delete_button;

    @FXML
    private Button teacher_absence_update_button;


    @FXML
    void addAbsence(ActionEvent event) {
        try{
            AbsenceDAO.insertAbsence(teacher_absence_name_text_field.getText(),teacher_absence_surename_text_field.getText(),teacher_absence_time_picker.getValue().toString() + " " + teacher_absence_date_picker.getValue().toString());
            searchAbsence();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    @FXML
    void searchAbsence() throws ClassNotFoundException {
        try {
            ObservableList<Absence> absenceData = AbsenceDAO.searchAbsence();
            populateAbsence(absenceData);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void populateAbsence(ObservableList<Absence> absenceData){
        teacher_absence_table_view.setItems(absenceData);
    }

    @FXML
    void deleteAbsence(ActionEvent event) throws ClassNotFoundException {
    AbsenceDAO.deleteAbsenceWithNameSurename(teacher_absence_name_text_field.getText(),teacher_absence_surename_text_field.getText());
    searchAbsence();
    }

    @FXML
    void updateAbsence(ActionEvent event) throws ClassNotFoundException {
        AbsenceDAO.updateAbsence(teacher_absence_name_text_field.getText(),teacher_absence_surename_text_field.getText(),teacher_absence_time_picker.getValue().toString() + " " + teacher_absence_date_picker.getValue().toString());
        searchAbsence();
    }

    @FXML
    public void initialize()throws ClassNotFoundException{
        searchAbsence();
        teacher_absence_name_column.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        teacher_absence_surename_column.setCellValueFactory(cellData->cellData.getValue().surenameProperty());
        teacher_absence_date_column.setCellValueFactory(cellData->cellData.getValue().dateProperty());
    }

}
