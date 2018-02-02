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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
        if (personIdText.getText().matches("[0-9]+")) {
            if (DialogUtils.deleteDialog().get() == ButtonType.OK) {

                int x = Integer.parseInt(personIdText.getText());
                PersonDAO.deletePersonWithId(x);

                resultArea.setText("Usunięto osobę z id: " + personIdText.getText());
                searchPersons();
            }
        } else {
            DialogUtils.informationDialog("Proszę o podanie liczby całkowitej nie ujemnej w polu ID Osoby");
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

        if (personIdText.getText().matches("[0-9]+")) {
            int x = Integer.parseInt(personIdText.getText());
            ObservableList<Person> personData = PersonDAO.searchPerson(x);
            personTable.setItems(personData);
        } else {
            DialogUtils.informationDialog("Proszę o podanie liczby całkowitej nie ujemnej w polu ID Osoby");
        }
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
        if (personIdText.getText().matches("[0-9]+")) {
            if (DialogUtils.updateDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(personIdText.getText());
                PersonDAO.updatePersonDetails(x, usernameText.getText(), passwordText.getText(), loginCombobox.getValue().toString());
                resultArea.setText("Dane zostaly zmienione dla osoby z id: " + personIdText.getText() + "\n");
                searchPersons();
            }
        } else {
            DialogUtils.informationDialog("Proszę o podanie liczby całkowitej nie ujemnej w polu ID Osoby");
        }

    }

    @FXML
    void onKeyReleased(KeyEvent event) {

        boolean isDisabled = (usernameText.getText().isEmpty() || passwordText.getText().isEmpty());
        addPersonBtn.setDisable(isDisabled);

    }

    //Inicjalizowanie klasy controlera
    @FXML
    private void initialize() {

        addPersonBtn.setDisable(true);
        searchPersons();
        loginCombobox.setItems(FXCollections.observableArrayList(Options.values()));

        personIdColumn.setCellValueFactory(cellData -> cellData.getValue().person_idProperty().asObject());
        personUserNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        personPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        personPartOfColumn.setCellValueFactory(cellData -> cellData.getValue().partofProperty());

        personTable.setRowFactory(tableView2 -> {
            final TableRow<Person> row = new TableRow<>();
            row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                final int index = row.getIndex();
                Person clickedRow = row.getItem();
                if (!row.isEmpty()) { // tutaj pobieranie danych z tabeli do pol
                    usernameText.setText(String.valueOf(clickedRow.getUsername()));
                    passwordText.setText(clickedRow.getPassword());
                    personIdText.setText(String.valueOf(clickedRow.getPerson_id()));
                }
                if (index >= 0 && index < personTable.getItems().size() && personTable.getSelectionModel().isSelected(index)) {
                    personTable.getSelectionModel().clearSelection();
                    usernameText.clear();
                    passwordText.clear();
                    personIdText.clear();
                    event.consume();
                }
            });
            return row;
        });
    }
}