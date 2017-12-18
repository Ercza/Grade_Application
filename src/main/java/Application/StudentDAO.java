package Application;

import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    private static Student getStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = null;
        if (rs.next()) {
            student = new Student();
            student.setStudent_id(rs.getInt("id_student"));
            student.setName(rs.getString("name"));
            student.setSurename(rs.getString("surename"));
            student.setLogin_id(rs.getInt("id_login"));
            student.setSubject_id(rs.getInt("id_subject"));
            student.setEmail(rs.getString("email"));
        }
        return student;
    }

    private static ObservableList<Student> getStudentList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        while (rs.next()) {
            Student student = new Student();
            student.setStudent_id(rs.getInt("id_student"));
            student.setName(rs.getString("name"));
            student.setSurename(rs.getString("surename"));
            student.setLogin_id(rs.getInt("id_login"));
            student.setSubject_id(rs.getInt("id_subject"));
            student.setEmail(rs.getString("email"));
            studentList.add(student);
        }
        return studentList;
    }

    public static Student searchStudents(String name, String surename) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM students WHERE name='" + name + "',surename='" + surename + "'";
        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);
            Student student = getStudentFromResultSet(rs);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Student> searchStudents() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM students";

        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);

            ObservableList<Student> studentList = getStudentList(rs);

            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateStudentDetails(String studentID, String name, String surename, String loginID, String subjectID, String email) throws SQLException, ClassNotFoundException {

        String updateStmt = "UPDATE students SET id_student='" + studentID + "',name='" + name + "',surename='" + surename + "',id_login='" + loginID + "',id_subject='" + subjectID + "',email='" + email + "'WHERE id_student = '" + studentID + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudentWithId(String studentId) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM students WHERE id_student= '" + studentId + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(String studentID, String name, String surename, String loginID, String subjectID, String email) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO students(id_student, name, surename, id_login, id_subject, email) VALUES ('" + studentID + "','" + name + "','" + surename + "','" + loginID + "','" + subjectID + "','" + email + "')";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
