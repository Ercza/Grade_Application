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

import java.sql.SQLException;

public class AdminPersonListController {

    @FXML
    private TextField personIdText;

    @FXML
    private TextField newEmailText;

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
    private TableColumn<Person, String> personEmailColumn;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField emailText;

    @FXML
    private Label resultArea;

    @FXML
    private JFXComboBox<Options> loginCombobox;

    //Usuwanie osoby z bazy dancyh o podanym id
    @FXML
    void deletePerson(ActionEvent event) throws ClassNotFoundException {
        try {
            PersonDAO.deletePersonWithId(personIdText.getText());
            resultArea.setText("Usunięto osobę z id: " + personIdText.getText());
            searchPersons();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    //Dodawanie osoby do bazy danych
    @FXML
    void insertPerson(ActionEvent event) throws ClassNotFoundException {

        try {
            PersonDAO.insertPerson(usernameText.getText(), passwordText.getText(), loginCombobox.getValue().toString());
            resultArea.setText("Dodano osobę: " + usernameText.getText() + " " + passwordText.getText() + " " + loginCombobox.getValue().toString());
            searchPersons();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
            System.out.println("Błąd wystąpił podczas dodawania osoby do bazy danych");
        }
    }

    //Szukaj osobe
    @FXML
    void searchPerson(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Pobieram dane o osobie
            Person person = PersonDAO.searchPerson(personIdText.getText());
            //wypelniamy tableview i wyswietlamy w TextArea
            populateAndShowPerson(person);
            searchPersons();
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Błąd wystąpił podczas wczytywania danych o osobie");
        }

    }

    //Szukaj osoby
    @FXML
    void searchPersons() throws ClassNotFoundException {
        try {
            personIdText.clear();
            emailText.clear();
            usernameText.clear();
            passwordText.clear();
            ObservableList<Person> personData = PersonDAO.searchPersons();
            populatePersons(personData);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    //Aktualizacja emaila osoby wpisany w newEmailText
    @FXML
    void updatePersonDetails(ActionEvent event) throws ClassNotFoundException {
        try {
            PersonDAO.updatePersonDetails(personIdText.getText(), usernameText.getText(), passwordText.getText(), loginCombobox.getValue().toString());
            resultArea.setText("Dane zostaly zmienione dla osoby z id: " + personIdText.getText() + "\n");
            searchPersons();

        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    //Inicjalizowanie klasy controlera
    @FXML
    private void initialize() throws  ClassNotFoundException {


        searchPersons();
        loginCombobox.setItems(FXCollections.observableArrayList(Options.values()));

        personIdColumn.setCellValueFactory(cellData -> cellData.getValue().person_idProperty().asObject());
        personUserNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        personPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        personPartOfColumn.setCellValueFactory(cellData -> cellData.getValue().partofProperty());

    }

    //Wypelnianie danych o osobie
    @FXML
    private void populatePerson(Person person) throws ClassNotFoundException {
        //Deklaracja ObservableList do wyswietlania w tabeli
        ObservableList<Person> personData = FXCollections.observableArrayList();
        //Dodajemy go do listy
        personData.add(person);
        //Ustawiamy w tabeli
        personTable.setItems(personData);
    }

    //Ustawienie informacji o Osobie w TextArea
    @FXML
    private void setPersonInfoTextArea(Person person) {
        resultArea.setText("Login: " + person.getUsername() + "\n" + "Hasło: " + person.getPassword());
    }

    //Wypelnianie tableview osobami i wyswietlenie osob w textarea
    @FXML
    private void populateAndShowPerson(Person person) throws ClassNotFoundException {
        if (person != null) {
            populatePerson(person);
            setPersonInfoTextArea(person);
        } else {
            resultArea.setText("Taka osoba nie istnieje!\n");
        }
    }

    //Wypelnianie tableview osobami
    @FXML
    private void populatePersons(ObservableList<Person> personData) throws ClassNotFoundException {
        //Ustawienie danych w tabeli
        personTable.setItems(personData);
    }


}