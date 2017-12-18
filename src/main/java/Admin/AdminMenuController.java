package Admin;

import Utils.FxmlUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminMenuController {


    public static final String ADMIN_NEWS_FXML = "/fxml/Admin/AdminNews.fxml";
    public static final String ADMIN_TEACHER_LIST_FXML = "/fxml/Admin/AdminPersonList.fxml";
    public static final String LOGIN_FXML = "/fxml/Login.fxml";

    private AdminController adminController;

    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private ToggleButton admin_toggle_button_person_list;

    @FXML
    private ToggleGroup admin_menu_toggle;

    @FXML
    private ToggleButton admin_toggle_button_news;

    @FXML
    private Button admin_button_logout;

    @FXML
    void makeLogout(ActionEvent event) {

        Stage stage = (Stage) this.admin_button_logout.getScene().getWindow();
        stage.close();

        Pane root = null;
        root = FxmlUtils.fxmlLoader(LOGIN_FXML);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Aplikacja e-Dziennik");
        stage.show();
    }


    @FXML
    void openNews() {
        adminController.setCenter(ADMIN_NEWS_FXML);
        System.out.println("Open News");
    }


    @FXML
    void openPersonList() {
        adminController.setCenter(ADMIN_TEACHER_LIST_FXML);
        System.out.println("Open TeacherList");
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }


}
