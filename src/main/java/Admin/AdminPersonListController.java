package Admin;

import Application.Options;
import Application.Person;
import Application.PersonDAO;
import Utils.DialogUtils;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Optional;

public class AdminPersonListController {

    @FXML
    private TextField personIdText;

    @FXML
    private Button searchPersonBtn;

    @FXML
    private Button deletePersonBtn;

    @FXML
    private Button updatePersonBtn;

    @FXML
    private Button searchPersonsBtn;

    @FXML
    private Button addPersonBtn;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Integer> personIdColumn;

    @FXML
    private TableColumn<Person, String> personUserNameColumn;

    @FXML
    private TableColumn<Person, String> personPasswordColumn;

    @FXML
    private TableColumn<Person, String> personPartOfColumn;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private Label resultArea;

    @FXML
    private JFXComboBox<Options> loginCombobox;

    //Usuwanie osoby z bazy dancyh osoby o podanym id
    @FXML
    void deletePerson() {

        if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(personIdText.getText());
            PersonDAO.deletePersonWithId(x);
            resultArea.setText("Usunięto osobę z id: " + personIdText.getText());
            searchPersons();
        }

    }

    //Dodawanie osoby do bazy danych
    @FXML
    void insertPerson() {

        if (DialogUtils.addDialog().get() == ButtonType.OK) {
            PersonDAO.insertPerson(usernameText.getText(), passwordText.getText(), loginCombobox.getValue().toString());
            resultArea.setText("Dodano osobę: " + usernameText.getText() + " " + passwordText.getText() + " " + loginCombobox.getValue().toString());
            searchPersons();
        }

    }

    //Szukaj osobe
    @FXML
    void searchPerson() {
        int x = Integer.parseInt(personIdText.getText());
        ObservableList<Person> personData = PersonDAO.searchPerson(x);
        personTable.setItems(personData);
    }

    //Szukaj osoby
    @FXML
    void searchPersons() {
        personIdText.clear();
        usernameText.clear();
        passwordText.clear();
        ObservableList<Person> personData = PersonDAO.searchPersons();
        personTable.setItems(personData);
    }

    //Aktualizacja emaila osoby wpisany w newEmailText
    @FXML
    void updatePersonDetails() {

        if (DialogUtils.updateDialog().get() == ButtonType.OK) {
            int x = Integer.parseInt(personIdText.getText());
            PersonDAO.updatePersonDetails(x, usernameText.getText(), passwordText.getText(), loginCombobox.getValue().toString());
            resultArea.setText("Dane zostaly zmienione dla osoby z id: " + personIdText.getText() + "\n");
            searchPersons();
        }

    }

    //Inicjalizowanie klasy controlera
    @FXML
    private void initialize() {

        searchPersons();
        loginCombobox.setItems(FXCollections.observableArrayList(Options.values()));

        personIdColumn.setCellValueFactory(cellData -> cellData.getValue().person_idProperty().asObject());
        personUserNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        personPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        personPartOfColumn.setCellValueFactory(cellData -> cellData.getValue().partofProperty());

    }
}