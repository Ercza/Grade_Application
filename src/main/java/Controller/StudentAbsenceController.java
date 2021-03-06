package Controller;

import DAO.Absence;
import DAO.AbsenceDAO;
import Utils.DialogUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    void searchAbsences() {
        if (student_absence_name_text_field.getText().matches("[a-zA-Z]+") || student_absence_surename_text_field.getText().matches("[a-zA-Z]")) {
            if (!student_absence_name_text_field.getText().isEmpty() || !student_absence_surename_text_field.getText().isEmpty()) {
                ObservableList<Absence> absences = AbsenceDAO.searchAbsence(student_absence_name_text_field.getText(), student_absence_surename_text_field.getText());
                student_absence_table_view.setItems(absences);
            }else{
                DialogUtils.informationDialog("Proszę podać prawidłowe imię oraz nazwisko");
            }
        } else {
            DialogUtils.informationDialog("Proszę podać prawidłowe imię oraz nazwisko");
        }
    }


    @FXML
    void refreshAbsence() {
        student_absence_name_text_field.clear();
        student_absence_surename_text_field.clear();
        ObservableList<Absence> absences = AbsenceDAO.searchAbsences();
        student_absence_table_view.setItems(absences);
    }

    @FXML
    public void initialize() {
        refreshAbsence();

        student_absence_id_column.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        student_absence_name_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        student_absence_surename_column.setCellValueFactory(cellData -> cellData.getValue().surenameProperty());
        student_absence_date_column.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    }
}

