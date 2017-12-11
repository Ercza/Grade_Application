package Student;

import Application.Absence;
import Application.AbsenceDAO;
import Utils.DialogUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class StudentAbsenceController {

    @FXML
    private TableView<Absence> student_absence_table_view;

    @FXML
    private TableColumn<Absence, String> student_absence_name_column;

    @FXML
    private TableColumn<Absence, String> student_absence_surename_column;

    @FXML
    private TableColumn<Absence, String> student_absence_date_column;

    @FXML
    private TextField student_absence_name_text_field;

    @FXML
    private TextField student_absence_surename_text_field;

    @FXML
    private Button student_absence_search_button;

    @FXML
    private Button student_absence_refresh_button;

    @FXML
    void searchAbsences(ActionEvent event) throws ClassNotFoundException {
        try {
            Absence absence = AbsenceDAO.searchAbsences(student_absence_name_text_field.getText(),student_absence_surename_text_field.getText());
            populateAndShowAbsence(absence);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void populateAndShowAbsence(Absence absence) throws ClassNotFoundException {
        if (absence != null) {
            populateAbsences(absence);
        } else {
            DialogUtils.informationDialog("Taka osoba nie istnieje");
        }
    }

    @FXML
    private void populateAbsences(Absence absence) throws ClassNotFoundException {
        ObservableList<Absence> absenceData = FXCollections.observableArrayList();
        absenceData.add(absence);
        student_absence_table_view.setItems(absenceData);
    }

    @FXML
    private void populateAbsence(ObservableList<Absence> absence) {
        student_absence_table_view.setItems(absence);
    }

    @FXML
    void refreshAbsence() throws ClassNotFoundException {
        try {
            ObservableList<Absence> absence = AbsenceDAO.searchAbsence();
            populateAbsence(absence);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws ClassNotFoundException {
        refreshAbsence();

        student_absence_name_column.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        student_absence_surename_column.setCellValueFactory(cellData->cellData.getValue().surenameProperty());
        student_absence_date_column.setCellValueFactory(cellData->cellData.getValue().dateProperty());
    }
}

