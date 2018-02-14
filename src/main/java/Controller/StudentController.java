package Controller;

import Utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class StudentController {

    @FXML
    private BorderPane studentBorderPane;

    @FXML
    private StudentMenuController leftStudentMenuButtonsController;

    @FXML
    private void initialize() {
        leftStudentMenuButtonsController.setStudentController(this);

    }

    //obsluga naszego lewego studentMenu
    public void setCenter(String fxmlPath) {
        studentBorderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

}
