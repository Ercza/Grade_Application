package Application;

import Utils.FxmlUtils;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentWindow {

    public static final String STUDENT_FXML_FXML = "/fxml/Student/StudentWindow.fxml";

    public void StudentWindow() {

            Stage studentStage = new Stage();
            Pane studentBorderPane = FxmlUtils.fxmlLoader(STUDENT_FXML_FXML);
            Scene scene = new Scene(studentBorderPane);
            studentStage.setScene(scene);
            studentStage.setTitle("Student");
            studentStage.show();
    }
}
