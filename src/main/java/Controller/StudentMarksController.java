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
    private Button refreshButton;


    @FXML
    void searchNoteE() {

        ObservableList<Notes> notes = NotesDAO.searchNoteE();
        tableView.setItems(notes);

    }


    @FXML
    public void initialize() {
        searchNoteE();

        name_column.setCellValueFactory(cell -> cell.getValue().nameProperty());
        surename_column.setCellValueFactory(cell -> cell.getValue().surenameProperty());
        subject_column.setCellValueFactory(cell -> cell.getValue().subjectProperty());
        note_column.setCellValueFactory(cell -> cell.getValue().noteProperty().asObject());
    }

}
