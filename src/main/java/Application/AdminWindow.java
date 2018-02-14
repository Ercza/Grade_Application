package Application;

import Utils.FxmlUtils;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminWindow {

    public static final String ADMIN_FXML_FXML = "/fxml/Admin/AdminWindow.fxml";

    public void AdminWindow() {

            Stage adminStage = new Stage();
            Pane adminBorderPane = FxmlUtils.fxmlLoader(ADMIN_FXML_FXML);
            Scene scene = new Scene(adminBorderPane);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin");
            adminStage.show();

    }

}
