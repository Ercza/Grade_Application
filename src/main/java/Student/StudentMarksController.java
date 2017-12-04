package Student;

import Application.Marks;
import Application.MarksDAO;
import Utils.DialogUtils;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class StudentMarksController {

    @FXML
    private TableColumn<Marks, String> student_id_subject_collumn;

    @FXML
    private TableColumn<Marks, Integer> student_marks_collumn;


    @FXML
    private TableView<Marks> student_marks_tableview;

    @FXML
    private Button student_button_load_marks;

    @FXML
    private JFXTextField student_marks_is_text_field;

    @FXML
    void loadMarks(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Marks> marksData = MarksDAO.searchMarks();
            populateMarks(marksData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateMarks(ObservableList<Marks> marksData) throws ClassNotFoundException {
        //Ustawienie danych w tabeli
        student_marks_tableview.setItems(marksData);
    }

    @FXML
    private void populateMarks(Marks marks) throws ClassNotFoundException {
        //Deklaracja ObservableList do wyswietlania w tabeli
        ObservableList<Marks> marksData = FXCollections.observableArrayList();
        //Dodajemy go do listy
        marksData.add(marks);
        //Ustawiamy w tabeli
        student_marks_tableview.setItems(marksData);
    }
    @FXML
    private void populateAndShowMarks(Marks marks) throws ClassNotFoundException {
        if (marks != null) {
            populateMarks(marks);
        } else {
            DialogUtils.informationDialog("Takie oceny nie istnieja");
        }
    }

    @FXML
    private void initialize() {

        student_id_subject_collumn.setCellValueFactory(cellData -> cellData.getValue().activityProperty());
        student_marks_collumn.setCellValueFactory(cellData-> cellData.getValue().markProperty().asObject());

    }

}