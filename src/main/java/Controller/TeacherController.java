package Controller;

import Utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class TeacherController {
    @FXML
    private BorderPane teacherBorderPane;

    @FXML
    private TeacherMenuController leftTeacherMenuButtonsController;

    @FXML
    private void initialize() {
        leftTeacherMenuButtonsController.setTeacherController(this);

    }

    //obsluga naszego lewego studentMenu
    public void setCenter(String fxmlPath) {
        teacherBorderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

}
