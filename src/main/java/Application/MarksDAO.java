package Application;

import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.security.auth.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksDAO {

    private static ObservableList<Marks> getMarkList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Marks> marksList = FXCollections.observableArrayList();

        while (rs.next()) {
            Marks marks = new Marks();
            marks.setId_mark(rs.getInt("id_mark"));
            marks.setMark(rs.getString("mark"));
            marksList.add(marks);
        }
        return marksList;
    }

    public static ObservableList<Marks> searchMarks() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM marks";

        try {
            ResultSet rsMarks = MysqlConnection.dbExecuteQuery(selectStmt);

            ObservableList<Marks> marksList = getMarkList(rsMarks);

            return marksList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Marks searchMarks(String subjectId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM marks WHERE ID_STUDENT='" + subjectId + "'";
        try {
            ResultSet rsMarks = MysqlConnection.dbExecuteQuery(selectStmt);
            Marks marks = getMarksFromResultSet(rsMarks);
            return marks;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static Marks getMarksFromResultSet(ResultSet rs) throws SQLException {
        Marks marks = null;
        if (rs.next()) {
            marks = new Marks();
            marks.setId_mark(rs.getInt("id_mark"));
            marks.setMark(rs.getString("mark"));
        }
        return marks;
    }
}
