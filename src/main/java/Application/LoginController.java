package Application;


import Admin.AdminWindow;
import Student.StudentWindow;
import Teacher.TeacherWindow;
import Utils.DialogUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginM loginM = new LoginM();

    @FXML
    private JFXTextField txtLogin;

    @FXML
    private Label lgnLabelData;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btLogin;

    @FXML
    private Label dbStatus;

    @FXML
    private JFXComboBox<Options> combobox;

    @FXML
    void makeLogin(ActionEvent event) { //funkcja logująca

        try {
            if (this.loginM.isLogin(this.txtLogin.getText(), this.txtPassword.getText(), this.combobox.getValue().toString())) {

                Stage stage = (Stage) this.btLogin.getScene().getWindow();
                stage.close();

                switch (this.combobox.getValue().toString()) {
                    case "Admin":
                        AdminWindow adminWindow = new AdminWindow();
                        adminWindow.AdminWindow();
                        break;
                    case "Student":
                        StudentWindow studentWindow = new StudentWindow();
                        studentWindow.StudentWindow();
                        break;
                    case "Teacher":
                        TeacherWindow teacherWindow = new TeacherWindow();
                        teacherWindow.TeacherWindow();
                        break;
                }

            } else {
                this.dbStatus.setText("Złe dane logowania");
            }
        } catch (SQLException e) {
            this.dbStatus.setText("Błąd SQL");
            DialogUtils.errorDialog(e.getMessage());
        }

    }
    @FXML
    void onKeyReleased(KeyEvent event) {
        boolean isDisabled = (txtLogin.getText().isEmpty() || txtPassword.getText().isEmpty());
        btLogin.setDisable(isDisabled);

    }

    public void initialize(URL location, ResourceBundle resources) {
        btLogin.setDisable(true);
        lgnLabelData.setText((new Date()).toString());

        if (loginM.isDbConnected()) { //sprawdzamy czy polaczylismy sie z baza danych
            this.dbStatus.setText("Podłączony do bazy danych");
        } else {
            this.dbStatus.setText("Nie podłączony do bazy danych");
        }

        this.combobox.setItems(FXCollections.observableArrayList(Options.values()));

    }


}
