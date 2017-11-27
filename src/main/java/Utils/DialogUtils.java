package Utils;


import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class DialogUtils {

    public static void errorDialog(String error){
        Alert alerterror =  new Alert(Alert.AlertType.ERROR);
        alerterror.setTitle("Bląd");
        alerterror.setHeaderText("Wystąpił nieoczekiwany błąd!");

        TextArea textArea = new TextArea(error);
        alerterror.getDialogPane().setContent(textArea);
        alerterror.showAndWait();
    }

    public static void informationDialog(String information){
        Alert alert =  new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");

        TextArea textArea =  new TextArea(information);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
}
