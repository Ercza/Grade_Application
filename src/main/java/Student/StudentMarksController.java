package Student;

import Application.*;
import Utils.NotesEntity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class StudentMarksController {

    @FXML
    private TableView<Join> tableView;

    @FXML
    private TableColumn<Join, Integer> id_student_column;

    @FXML
    private TableColumn<Join, Integer> id_teacher_column;

    @FXML
    private TableColumn<Join, Integer> id_subject_column;

    @FXML
    private TableColumn<Join, Integer> id_note_column;

    @FXML
    private TextField id_student_field;

    @FXML
    private TextField id_teacher_field;

    @FXML
    private TextField id_subject_field;

    @FXML
    private TextField id_mark_field;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button refreshButton;


    @FXML
    public void readAllNote() {
        id_student_field.clear();
        id_teacher_field.clear();
        id_subject_field.clear();
        id_mark_field.clear();
        ObservableList<Join> notes = NotesDAO.readAll();
        tableView.setItems(notes);
    }

    @FXML
    public void deleteNote() {
        int z = Integer.parseInt(id_student_field.getText());
        NotesDAO.delete(z);

    }

    @FXML
    public void updateNote() {
        int x = Integer.parseInt(id_student_field.getText());
        int y = Integer.parseInt(id_teacher_field.getText());
        int w = Integer.parseInt(id_subject_field.getText());
        int z = Integer.parseInt(id_mark_field.getText());
        NotesDAO.update(x, y, w, z);

    }

    @FXML
    public void createNote() {
        int x = Integer.parseInt(id_student_field.getText());
        int y = Integer.parseInt(id_teacher_field.getText());
        int w = Integer.parseInt(id_subject_field.getText());
        int z = Integer.parseInt(id_mark_field.getText());

        NotesDAO.create(x, y, w, z);
    }

    @FXML
    private void initialize() {
        readAllNote();
        id_student_column.setCellValueFactory(cell->cell.getValue().id_studentProperty().asObject());
        id_teacher_column.setCellValueFactory(cell->cell.getValue().id_teacherProperty().asObject());
        id_subject_column.setCellValueFactory(cell->cell.getValue().id_subjectProperty().asObject());
        id_note_column.setCellValueFactory(cell->cell.getValue().id_markProperty().asObject());

    }

}