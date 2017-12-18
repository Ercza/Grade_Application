package Student;

import Application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class StudentMarksController {

    @FXML
    private TableView<Join> tableView;

    @FXML
    private TableColumn<Join, Integer> id_column;

    @FXML
    private TableColumn<Join, String> name_column;

    @FXML
    private TableColumn<Join, String> surename_column;

    @FXML
    private TableColumn<Join, String> subject_column;

    @FXML
    private TableColumn<Join, String> mark_column;

    @FXML
    void RefreshNews() throws ClassNotFoundException, SQLException {
        try {

            ObservableList<Join> subjects = SubjectDAO.getObject();

            populateNews(subjects);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void populateNews(ObservableList<Join> list) {

        tableView.setItems(list);
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {

        RefreshNews();

        id_column.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        name_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surename_column.setCellValueFactory(cellData -> cellData.getValue().surenameProperty());
        subject_column.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        mark_column.setCellValueFactory(cellData -> cellData.getValue().markProperty());

    }

}