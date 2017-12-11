package Application;

import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksDAO {

    private static ObservableList<Marks> getMarkList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Marks> marksList = FXCollections.observableArrayList();

        while (rs.next()) {
            Marks marks = new Marks();

            marks.setStudent_id(rs.getInt("ID_STUDENT"));
            marks.setActivity(rs.getString("ID_SUBJECT"));
            marks.setMark(rs.getInt("MARK"));
            marksList.add(marks);
        }
        return marksList;
    }

    public static ObservableList<Marks> searchMarks() throws ClassNotFoundException, SQLException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM marks";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsMarks = MysqlConnection.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Marks> marksList = getMarkList(rsMarks);

            //Return person object
            return marksList;
        } catch (SQLException e) {
            e.printStackTrace();
            //Return exception
            throw e;
        }
    }

    public static Marks searchMarks(String subjectId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM marks WHERE ID_STUDENT='" + subjectId + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsMarks = MysqlConnection.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Marks marks = getMarksFromResultSet(rsMarks);
            //Return employee object
            return marks;
        } catch (SQLException e) {
            e.printStackTrace();
            //Return exception
            throw e;
        }
    }

    private static Marks getMarksFromResultSet(ResultSet rs) throws SQLException {
        Marks marks = null;
        if (rs.next()) {
            marks = new Marks();
            marks.setStudent_id(rs.getInt("ID_STUDENT"));
            marks.setActivity(rs.getString("ID_SUBJECT"));
            marks.setMark(rs.getInt("MARK"));
        }
        return marks;
    }
}
