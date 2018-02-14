package Controller;

import DAO.News;
import DAO.NewsDAO;
import Utils.DialogUtils;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class AdminNewsController {

    @FXML
    private TableView<News> admin_news_tableview;

    @FXML
    private TableColumn<News, Integer> admin_news_id_column;

    @FXML
    private TableColumn<News, String> admin_news_description_column;

    @FXML
    private Button admin_news_button_add_news;

    @FXML
    private Button admin_news_button_update;

    @FXML
    private Button admin_news_button_delete;

    @FXML
    private Button admin_news_button_refresh;

    @FXML
    private TextArea admin_news_text_area;

    @FXML
    private JFXTextField admin_text_field_id;

    @FXML
    void adminAddNews(ActionEvent event) {
        if (!admin_news_text_area.getText().isEmpty()) {
            if (DialogUtils.addDialog().get() == ButtonType.OK) {
                NewsDAO.insertNews(admin_news_text_area.getText());
                adminRefreshNews();
            }
        } else {
            DialogUtils.informationDialog("Pole komunikatu jest puste");
        }
    }

    @FXML
    void adminRefreshNews() {
        admin_news_text_area.clear();
        admin_text_field_id.clear();
        ObservableList<News> news = NewsDAO.searchNews();
        admin_news_tableview.setItems(news);
    }

    @FXML
    void adminUpdateNews(ActionEvent event) {
        if (admin_text_field_id.getText().matches("[0-9]+")) {
            if (DialogUtils.updateDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(admin_text_field_id.getText());
                NewsDAO.updateNews(x, admin_news_text_area.getText());
                adminRefreshNews();
            }
        } else {
            DialogUtils.informationDialog("Proszę o podanie liczby całkowitej nie ujemnej w polu news_id");
        }
    }

    @FXML
    void adminDeleteNews(ActionEvent event) {
        if (admin_text_field_id.getText().matches("[0-9]+")) {
            if (DialogUtils.deleteDialog().get() == ButtonType.OK) {
                int x = Integer.parseInt(admin_text_field_id.getText());
                NewsDAO.deleteNewsWithId(x);
                adminRefreshNews();
            }
        } else {
            DialogUtils.informationDialog("Proszę o podanie liczby całkowitej nie ujemnej w polu news_id");
        }
    }

    public void initialize() {
        adminRefreshNews();

        admin_news_id_column.setCellValueFactory(cellData -> cellData.getValue().news_idProperty().asObject());
        admin_news_description_column.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        admin_news_tableview.setRowFactory(tableView2 -> {
            final TableRow<News> row = new TableRow<>();
            row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                final int index = row.getIndex();
                News clickedRow = row.getItem();
                if (!row.isEmpty()) { // tutaj pobieranie danych z tabeli do pol
                    admin_text_field_id.setText(String.valueOf(clickedRow.getNews_id()));
                    admin_news_text_area.setText(clickedRow.getDescription());

                }
                if (index >= 0 && index < admin_news_tableview.getItems().size() && admin_news_tableview.getSelectionModel().isSelected(index)) {
                    admin_news_tableview.getSelectionModel().clearSelection();
                    admin_text_field_id.clear();
                    admin_news_text_area.clear();
                    event.consume();
                }
            });
            return row;
        });

    }

}

