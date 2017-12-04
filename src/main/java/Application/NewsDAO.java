package Application;

import Utils.DialogUtils;
import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsDAO {
    public static ObservableList<News> searchNews() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM news";

        try {
            ResultSet rsNews = MysqlConnection.dbExecuteQuery(selectStmt);

            ObservableList<News> newsList = getNewsList(rsNews);

            //Return person object
            return newsList;
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
            //Return exception
            throw e;
        }
    }

    private static ObservableList<News> getNewsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<News> newsList = FXCollections.observableArrayList();

        while (rs.next()) {
            News news = new News();

            news.setNews_id(rs.getInt("id"));
            news.setDescription(rs.getString("description"));
            newsList.add(news);
        }
        return newsList;
    }

    public static void updateNews(String newsId, String description) throws SQLException, ClassNotFoundException {

        String updateStmt = "UPDATE news SET description='" + description + "'WHERE id= " + newsId;

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public static void deleteNewsWithId(String newsId) throws SQLException, ClassNotFoundException {

        String updateStmt = "DELETE FROM news WHERE id= " + newsId;

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertNews(String description) throws SQLException, ClassNotFoundException {

        String updateStmt = "INSERT INTO news(description) VALUES ('" + description + "')";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
