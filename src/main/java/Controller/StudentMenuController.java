package Controller;


import Utils.DialogUtils;
import Utils.FxmlUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentMenuController {

    public static final String LOGIN_FXML = "/fxml/Login.fxml";
    public static final String STUDENT_NEWS_FXML = "/fxml/Student/StudentNews.fxml";
    public static final String STUDENT_MARKS_FXML = "/fxml/Student/StudentMarks.fxml";
    public static final String STUDENT_ATTENTIONS_FXML = "/fxml/Student/StudentAttentions.fxml";
    public static final String STUDENT_ABSENCE_FXML = "/fxml/Student/StudentAbsence.fxml";

    private StudentController studentController;

    @FXML
    private Button student_button_absence;

    @FXML
    private Button student_button_marks;

    @FXML
    private Button student_button_comments;

    @FXML
    private Button student_button_news;

    @FXML
    private Button student_button_logout;


    @FXML
    void makeLogout(ActionEvent event) {
        if (DialogUtils.logoutDialog().get() == ButtonType.OK) {
            Stage stage = (Stage) this.student_button_logout.getScene().getWindow();
            stage.close();

            Pane root = null;
            root = FxmlUtils.fxmlLoader(LOGIN_FXML);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Aplikacja e-Dziennik");
            stage.show();
        }
    }

    @FXML
    void openStudentNews(ActionEvent event) {
        System.out.println("Open News");
        studentController.setCenter(STUDENT_NEWS_FXML);
    }

    @FXML
    void openStudentComments(ActionEvent event) {
        studentController.setCenter(STUDENT_ATTENTIONS_FXML);
        System.out.println("Open Student Comments");

    }

    @FXML
    void openStudentMarks(ActionEvent event) {
        studentController.setCenter(STUDENT_MARKS_FXML);
        System.out.println("Open Student Marks");
    }

    @FXML
    void openStudentAbsence(ActionEvent event) {
        studentController.setCenter(STUDENT_ABSENCE_FXML);

    }

    public void setStudentController(StudentController studentController) {
        this.studentController = studentController;
    }


}
