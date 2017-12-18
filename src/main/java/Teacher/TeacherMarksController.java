package Teacher;


import Application.Subject;
import Application.SubjectDAO;
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
        try{
            SubjectDAO.insertSubject(teacher_subject_text_field_id.getText(),teacher_subject_text_field_name.getText(),teacher_subject_text_field_teacherID.getText(),teacher_subject_text_field_studentID.getText(),teacher_subject_text_field_markID.getText());
            searchSubjects();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @FXML
    void deleteSubject(ActionEvent event) throws ClassNotFoundException {
        SubjectDAO.deleteSubjectWithID(teacher_subject_text_field_id.getText());
        searchSubjects();
    }

    @FXML
    void searchSubjects() throws ClassNotFoundException {
        try {
            teacher_subject_text_field_id.clear();
            teacher_subject_text_field_markID.clear();
            teacher_subject_text_field_name.clear();
            teacher_subject_text_field_studentID.clear();
            teacher_subject_text_field_teacherID.clear();
            ObservableList<Subject> subjectData = SubjectDAO.searchSubjects();
            populateSubjects(subjectData);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    void searchSubject(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Subject subject = SubjectDAO.searchSubject(teacher_subject_text_field_id.getText());
            populateAndShowSubject(subject);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void populateAndShowSubject(Subject subject) throws ClassNotFoundException {
        if (subject != null) {
            populateSubject(subject);
        } else {
            DialogUtils.informationDialog("Nie ma takiego przedmiotu o danym ID");
        }
    }
    @FXML
    private void populateSubject(Subject subject) throws ClassNotFoundException {
        ObservableList<Subject> subjectData = FXCollections.observableArrayList();
        subjectData.add(subject);
        teacher_subject_table_view.setItems(subjectData);
    }

    @FXML
    private void populateSubjects(ObservableList<Subject> subjectData){
       teacher_subject_table_view.setItems(subjectData);
    }

    @FXML
    void updateSubject(ActionEvent event) throws ClassNotFoundException {
        SubjectDAO.updateSubject(teacher_subject_text_field_id.getText(),teacher_subject_text_field_name.getText(),teacher_subject_text_field_teacherID.getText(),teacher_subject_text_field_studentID.getText(),teacher_subject_text_field_markID.getText());
        searchSubjects();
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