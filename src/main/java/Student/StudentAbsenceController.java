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
    private TableColumn<Absence, Integer> student_absence_id_column;

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
    void searchAbsences(){
        ObservableList<Absence> absences = AbsenceDAO.searchAbsence(student_absence_name_text_field.getText(),student_absence_surename_text_field.getText());
        student_absence_table_view.setItems(absences);

    }


    @FXML
    void refreshAbsence(){
        ObservableList<Absence> absences = AbsenceDAO.searchAbsences();
        student_absence_table_view.setItems(absences);
    }

    @FXML
    public void initialize(){
        refreshAbsence();

        student_absence_id_column.setCellValueFactory(cell->cell.getValue().idProperty().asObject());
        student_absence_name_column.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        student_absence_surename_column.setCellValueFactory(cellData->cellData.getValue().surenameProperty());
        student_absence_date_column.setCellValueFactory(cellData->cellData.getValue().dateProperty());
    }
}

