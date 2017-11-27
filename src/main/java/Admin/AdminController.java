package Admin;

import Utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class AdminController {

    @FXML
    private BorderPane adminBorderPane;

    @FXML
    private AdminMenuController leftMenuButtonsController;

    @FXML
    private void initialize() {
        leftMenuButtonsController.setAdminController(this);

    }

    //obsluga naszego lewego adminMenu
    public void setCenter(String fxmlPath) {
        adminBorderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

}


