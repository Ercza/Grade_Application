package Application;

import Utils.FxmlUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final String LOGIN_FXML = "/fxml/Login.fxml";

    @Override
    public void start(Stage primaryStage) throws IOException {

        Pane root = FxmlUtils.fxmlLoader(LOGIN_FXML);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aplikacja e-Dziennik");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
