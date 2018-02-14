package Application;

import Utils.FxmlUtils;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TeacherWindow {

    public static final String TEACHER_FXML_FXML = "/fxml/Teacher/TeacherWindow.fxml";

    public void TeacherWindow() {

            Stage teacherStage = new Stage();
            Pane root = FxmlUtils.fxmlLoader(TEACHER_FXML_FXML);
            Scene scene = new Scene(root);
            teacherStage.setScene(scene);
            teacherStage.setTitle("Nauczyciel");
            teacherStage.show();
    }
}
