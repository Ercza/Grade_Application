package Controller;

import DAO.Notes;
import DAO.NotesDAO;
import Utils.DialogUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentMarksController {

    @FXML
    private TableView<Notes> tableView;

    @FXML
    private TableColumn<Notes, String> name_column;

    @FXML
    private TableColumn<Notes, String> surename_column;

    @FXML
    private TableColumn<Notes, String> subject_column;

    @FXML
    private TableColumn<Notes, Integer> note_column;

    @FXML
    private TextField name_student_field;

    @FXML
    private TextField surename_teacher_field;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    @FXML
    void readAllNotes() {
        name_student_field.clear();
        surename_teacher_field.clear();
        ObservableList<Notes> notes = NotesDAO.searchNotes();
        tableView.setItems(notes);

    }

    @FXML
    void searchNote() {
        if (!name_student_field.getText().isEmpty() && !surename_teacher_field.getText().isEmpty()) {
            ObservableList<Notes> note = NotesDAO.searchNote(name_student_field.getText(), surename_teacher_field.getText());
            tableView.setItems(note);
        } else {
            DialogUtils.informationDialog("Proszę podać prawidłowe Imię oraz Nazwisko");
        }
    }


    @FXML
    public void initialize() {
        readAllNotes();

        name_column.setCellValueFactory(cell -> cell.getValue().nameProperty());
        surename_column.setCellValueFactory(cell -> cell.getValue().surenameProperty());
        subject_column.setCellValueFactory(cell -> cell.getValue().subjectProperty());
        note_column.setCellValueFactory(cell -> cell.getValue().noteProperty().asObject());
    }

}
