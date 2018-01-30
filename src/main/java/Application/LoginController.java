package Application;


import Admin.AdminWindow;
import Student.StudentWindow;
import Teacher.TeacherWindow;
import Utils.LoginEntity;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

    public boolean isLogin(String user, String pass, String option) {

        EntityManager em = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            Query q = em.createQuery("select u from LoginEntity u where u.username = ?1 and u.password = ?2 and u.partof = ?3", LoginEntity.class);
            q.setParameter(1, user);
            q.setParameter(2, pass);
            q.setParameter(3, option);

            transaction.commit();

            LoginEntity le = (LoginEntity) q.getSingleResult();

            if (user.equalsIgnoreCase(le.getUsername()) && pass.equals(le.getPassword()) && option.equals(le.getPartof())) {
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            dbStatus.setText("Złe dane logowania");
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return false;
        } finally {
            em.close();
        }

    }

    @FXML
    void makeLogin(ActionEvent event) { //funkcja logująca

        if (this.isLogin(this.txtLogin.getText(), this.txtPassword.getText(), this.combobox.getValue().toString())) {

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

    }
    @FXML
    void onKeyReleased(KeyEvent event) {
        boolean isDisabled = (txtLogin.getText().isEmpty() || txtPassword.getText().isEmpty());
        btLogin.setDisable(isDisabled);
    }

    public void initialize(URL location, ResourceBundle resources) {
        btLogin.setDisable(true);
        lgnLabelData.setText((new Date()).toString());

        this.combobox.setItems(FXCollections.observableArrayList(Options.values()));

    }


}
