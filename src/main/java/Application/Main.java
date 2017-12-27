package Application;

import Utils.FxmlUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main extends Application {

    public static final String LOGIN_FXML = "/fxml/Login.fxml";

    public static EntityManagerFactory emf;

    @Override
    public void start(Stage primaryStage) throws IOException {

        emf = Persistence.createEntityManagerFactory("school");

        Pane root = FxmlUtils.fxmlLoader(LOGIN_FXML);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aplikacja e-Dziennik");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

        emf.close();

    }
}
