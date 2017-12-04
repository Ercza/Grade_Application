package Teacher;

import Utils.FxmlUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TeacherMenuController {

    public static final String LOGIN_FXML = "/fxml/Login.fxml";
    public static final String TEACHER_NEWS_FXML = "/fxml/Teacher/TeacherNews.fxml";
    public static final String TEACHER_MARKS_FXML = "/fxml/Teacher/TeacherMarks.fxml";

    private TeacherController teacherController;

    @FXML
    private Button teacher_button_marks;

    @FXML
    private Button teacher_button_comments;

    @FXML
    private Button teacher_button_absence;

    @FXML
    private Button teacher_button_news;

    @FXML
    private Button teacher_button_logout;

    @FXML
    void makeLogout(ActionEvent event) {
        Stage stage = (Stage) this.teacher_button_logout.getScene().getWindow();
        stage.close();

        Pane root = null;
        root = FxmlUtils.fxmlLoader(LOGIN_FXML);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Aplikacja e-Dziennik");
        stage.show();

    }

    @FXML
    void openTeacherAbsence(ActionEvent event) {

    }

    @FXML
    void openTeacherComments(ActionEvent event) {

    }

    @FXML
    void openTeacherMarks(ActionEvent event) {
        teacherController.setCenter(TEACHER_MARKS_FXML);

    }

    @FXML
    void openTeacherNews(ActionEvent event) {
        teacherController.setCenter(TEACHER_NEWS_FXML);

    }

    public void setTeacherController(TeacherController teacherController) {
        this.teacherController = teacherController;
    }

}
