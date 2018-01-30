package Utils;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.util.Optional;

public class DialogUtils {

    public static void errorDialog(String error) {
        Alert alerterror = new Alert(Alert.AlertType.ERROR);
        alerterror.setTitle("Bląd");
        alerterror.setHeaderText("Wystąpił nieoczekiwany błąd!");

        TextArea textArea = new TextArea(error);
        alerterror.getDialogPane().setContent(textArea);
        alerterror.showAndWait();
    }

    public static void informationDialog(String information) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(information);

        alert.showAndWait();
    }

    public static Optional<ButtonType> addDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Czy napewno chcesz dodać ?");
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public static Optional<ButtonType> deleteDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Czy napewno chcesz usunąć ?");
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public static Optional<ButtonType> updateDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Czy napewno chcesz zaktualizować ?");
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public static Optional<ButtonType> logoutDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Czy napewno chcesz się wylogować ?");
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }


}
