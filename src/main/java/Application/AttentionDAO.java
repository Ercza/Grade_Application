package Application;

import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttentionDAO {

    public static ObservableList<Attention> searchAttentions() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM attentions";

        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);

            ObservableList<Attention> attentionList = getAttentionList(rs);

            return attentionList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Attention> getAttentionList(ResultSet rs) throws SQLException {
        ObservableList<Attention> attentionsList = FXCollections.observableArrayList();

        while (rs.next()) {
            Attention att = new Attention();
            att.setStudent_id(rs.getInt("student_id"));
            att.setAttention(rs.getString("attention"));
            att.setDate(rs.getString("date"));
            attentionsList.add(att);
        }
        return attentionsList;
    }

    public static void updateAttention(String id, String attention, String date) throws ClassNotFoundException {
        String updateStmt = "UPDATE attentions SET attention='" + attention + "',date='" + date + "'WHERE student_id = '" + id + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAttentionWithId(String attentionId) throws ClassNotFoundException {
        String updateStmt = "DELETE FROM attentions WHERE student_id= '" + attentionId + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertAttention(String attention, String date) throws ClassNotFoundException {

        String updateStmt = "INSERT INTO attentions(attention, date) VALUES ('" + attention + "','" + date + "')";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
