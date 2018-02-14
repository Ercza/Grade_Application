package Controller;

import DAO.Absence;
import DAO.Notes;
import DAO.NotesDAO;
import Utils.DialogUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class TeacherMarksController {

    @FXML
    private TableView<Notes> teacher_subject_table_view;

    @FXML
    private TableColumn<Notes, Integer> teacher_mark_id_column;

    @FXML
    private TableColumn<Notes, String> teacher_mark_name_column;

    @FXML
    private TableColumn<Notes, String> teacher_mark_surename_column;

    @FXML
    private TableColumn<Notes, String> teacher_mark_subject_column;

    @FXML
    private TableColumn<Notes, Integer> mark_column;

    @FXML
    private TextField teacher_id_text_field;

    @FXML
    private TextField teacher_name_text_field;

    @FXML
    private TextField teacher_surename_text_field;

    @FXML
    private TextField teacher_subject_text_field;

    @FXML
    private TextField teacher_mark_text_field;

    @FXML
    private Button teacher_subject_button_add;

    @FXML
    private Button teacher_subject_button_update;

    @FXML
    private Button teacher_subject_button_delete;

    @FXML
    private Button teacher_subject_button_search;

    @FXML
    private Button teacher_mark_button_refresh;

    @FXML
    void addMark() {
        if (!teacher_name_text_field.getText().isEmpty() && !teacher_surename_text_field.getText().isEmpty() && !teacher_subject_text_field.getText().isEmpty() && !teacher_mark_text_field.getText().isEmpty()) {
            if (DialogUtils.addDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(teacher_mark_text_field.getText());
                NotesDAO.insertNote(teacher_name_text_field.getText(), teacher_surename_text_field.getText(), teacher_subject_text_field.getText(), x);
                searchSubjects();
            } else {
                DialogUtils.informationDialog("Proszę wypełnić formularze");
            }
        } else {
            DialogUtils.informationDialog("Proszę wypełnić formularze");
        }
    }

    @FXML
    void deleteMark() {
        if (teacher_id_text_field.getText().matches("[0-9]+")) {
            if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(teacher_id_text_field.getText());
                NotesDAO.deleteNoteWithId(x);
                searchSubjects();
            }
        } else {
            DialogUtils.informationDialog("Proszę podać poprawne ID");
        }

    }

    @FXML
    void searchMark() {
        if (teacher_name_text_field.getText().matches("[a-zA-Z]+") || teacher_surename_text_field.getText().matches("[a-zA-Z]")) {
            if (!teacher_name_text_field.getText().isEmpty() || !teacher_surename_text_field.getText().isEmpty()) {
                ObservableList<Notes> note = NotesDAO.searchNote(teacher_name_text_field.getText(), teacher_surename_text_field.getText());
                teacher_subject_table_view.setItems(note);
            } else {
                DialogUtils.informationDialog("Proszę podać prawidłowe imię oraz nazwisko");
            }
        } else {
            DialogUtils.informationDialog("Proszę podać prawidłowe imię oraz nazwisko");
        }

    }

    @FXML
    void searchSubjects() {
        teacher_id_text_field.clear();
        teacher_name_text_field.clear();
        teacher_surename_text_field.clear();
        teacher_subject_text_field.clear();
        teacher_mark_text_field.clear();
        ObservableList<Notes> notes = NotesDAO.searchNotes();
        teacher_subject_table_view.setItems(notes);
    }

    @FXML
    void updateMark() {
        if (teacher_id_text_field.getText().matches("[0-9]+")) {
            if (DialogUtils.updateDialog().get() == ButtonType.OK) {
                int y = Integer.parseInt(teacher_mark_text_field.getText());
                int x = Integer.parseInt(teacher_id_text_field.getText());
                NotesDAO.updateNote(x, teacher_name_text_field.getText(), teacher_surename_text_field.getText(), teacher_subject_text_field.getText(), y);
                searchSubjects();
            }
        } else {
            DialogUtils.informationDialog("Proszę podać poprawne ID");
        }
    }

    @FXML
    public void initialize() {
        searchSubjects();

        teacher_mark_id_column.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        teacher_mark_name_column.setCellValueFactory(cell -> cell.getValue().nameProperty());
        teacher_mark_surename_column.setCellValueFactory(cell -> cell.getValue().surenameProperty());
        teacher_mark_subject_column.setCellValueFactory(cell -> cell.getValue().subjectProperty());
        mark_column.setCellValueFactory(cell -> cell.getValue().noteProperty().asObject());

        teacher_subject_table_view.setRowFactory(tableView2 -> {
            final TableRow<Notes> row = new TableRow<>();
            row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                final int index = row.getIndex();
                Notes clickedRow = row.getItem();
                if (!row.isEmpty()) { // tutaj pobieranie danych z tabeli do pol
                    teacher_id_text_field.setText(String.valueOf(clickedRow.getId()));
                    teacher_name_text_field.setText(clickedRow.getName());
                    teacher_surename_text_field.setText(clickedRow.getSurename());
                    teacher_subject_text_field.setText(clickedRow.getSubject());
                    teacher_mark_text_field.setText(String.valueOf(clickedRow.getNote()));
                }
                if (index >= 0 && index < teacher_subject_table_view.getItems().size() && teacher_subject_table_view.getSelectionModel().isSelected(index)) {
                    teacher_subject_table_view.getSelectionModel().clearSelection();
                    teacher_id_text_field.clear();
                    teacher_name_text_field.clear();
                    teacher_surename_text_field.clear();
                    teacher_subject_text_field.clear();
                    teacher_mark_text_field.clear();
                    event.consume();
                }
            });
            return row;
        });
    }
}
