package Application;


import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AbsenceDAO {

    public static ObservableList<Absence> searchAbsence() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM absences";

        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);

            ObservableList<Absence> absenceList = getAbsenceList(rs);

            return absenceList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Absence searchAbsences(String name, String surename) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM absences WHERE name='" + name + "'AND surename='" + surename + "'";
        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);
            Absence absence = getAbsenceFromResultSet(rs);
            return absence;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static Absence getAbsenceFromResultSet(ResultSet rs) throws SQLException {
        Absence absence = null;
        if (rs.next()) {
            absence = new Absence();
            absence.setName(rs.getString("name"));
            absence.setSurename(rs.getString("surename"));
            absence.setDate(rs.getString("date"));
        }
        return absence;
    }

    public static ObservableList<Absence> getAbsenceList(ResultSet rs) throws SQLException {
        ObservableList<Absence> absenceList = FXCollections.observableArrayList();

        while (rs.next()) {
            Absence absence = new Absence();
            absence.setName(rs.getString("name"));
            absence.setSurename(rs.getString("surename"));
            absence.setDate(rs.getString("date"));
            absenceList.add(absence);
        }
        return absenceList;
    }

    public static void updateAbsence(String name, String surename, String date) throws ClassNotFoundException {
        String updateStmt = "UPDATE absences SET name='" + name + "',surename='" + surename + "',date='" + date + "'WHERE name = '" + name + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAbsenceWithNameSurename(String name, String surename) throws ClassNotFoundException {
        String updateStmt = "DELETE FROM absences WHERE name='" + name + "'and surename='" + surename + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertAbsence(String name, String surename, String date) throws ClassNotFoundException {

        String updateStmt = "INSERT INTO absences(name, surename, date) VALUES ('" + name + "','" + surename + "','" + date + "')";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
