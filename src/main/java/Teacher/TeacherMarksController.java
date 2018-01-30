package Teacher;


import Application.Subject;
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


public class TeacherMarksController {

    @FXML
    private TableView<Subject> teacher_subject_table_view;

    @FXML
    private TableColumn<Subject, Integer> teacher_mark_id_column;

    @FXML
    private TableColumn<Subject, String> teacher_mark_name_column;

    @FXML
    private TableColumn<Subject, Integer> teacher_mark_teacherID_column;

    @FXML
    private TableColumn<Subject, Integer> teacher_mark_studentID_column;

    @FXML
    private TableColumn<Subject, Integer> teacher_markID_column;

    @FXML
    private TextField teacher_subject_text_field_id;

    @FXML
    private TextField teacher_subject_text_field_name;

    @FXML
    private TextField teacher_subject_text_field_teacherID;

    @FXML
    private TextField teacher_subject_text_field_studentID;

    @FXML
    private TextField teacher_subject_text_field_markID;

    @FXML
    private Button teacher_subject_button_add;

    @FXML
    private Button teacher_subject_button_update;

    @FXML
    private Button teacher_subject_button_delete;

    @FXML
    private Button teacher_subject_button_search;

    @FXML
    void addSubject(ActionEvent event) {

    }

    @FXML
    void deleteSubject(ActionEvent event) throws ClassNotFoundException {

    }

    @FXML
    void searchSubjects() throws ClassNotFoundException {

    }
    @FXML
    void searchSubject(ActionEvent event) throws SQLException, ClassNotFoundException {


    }

    @FXML
    void updateSubject(ActionEvent event) throws ClassNotFoundException {
    }

    @FXML
    public void initialize() throws ClassNotFoundException {
        searchSubjects();

        teacher_mark_id_column.setCellValueFactory(cellData->cellData.getValue().subjectIDProperty().asObject());
        teacher_mark_name_column.setCellValueFactory(cellData->cellData.getValue().subjectNameProperty());
        teacher_mark_teacherID_column.setCellValueFactory(cellData->cellData.getValue().teacherIDProperty().asObject());
        teacher_mark_studentID_column.setCellValueFactory(cellData->cellData.getValue().studentIDProperty().asObject());
        teacher_markID_column.setCellValueFactory(cellData->cellData.getValue().markIDProperty().asObject());

    }


}