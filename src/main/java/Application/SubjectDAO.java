package Application;

import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAO {

    public static ObservableList<Subject> searchSubjects() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM subjects";

        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);

            ObservableList<Subject> subjectList = getSubjectList(rs);

            return subjectList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Subject searchSubject(String subjectid) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM subjects WHERE id_subject='" + subjectid + "'";
        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);
            Subject subject = getSubjectFromResultSet(rs);
            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static Subject getSubjectFromResultSet(ResultSet rs) throws SQLException {
        Subject subject = null;
        if (rs.next()) {
            subject = new Subject();
            subject.setSubjectID(rs.getInt("id_subject"));
            subject.setSubjectName(rs.getString("subject_name"));
            subject.setTeacherID(rs.getInt("id_teacher"));
            subject.setStudentID(rs.getInt("id_student"));
            subject.setMarkID(rs.getInt("id_oceny"));
        }
        return subject;
    }

    public static ObservableList<Subject> getSubjectList(ResultSet rs) throws SQLException {
        ObservableList<Subject> subjectList = FXCollections.observableArrayList();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setSubjectID(rs.getInt("id_subject"));
            subject.setSubjectName(rs.getString("subject_name"));
            subject.setTeacherID(rs.getInt("id_teacher"));
            subject.setStudentID(rs.getInt("id_student"));
            subject.setMarkID(rs.getInt("id_oceny"));
            subjectList.add(subject);
        }
        return subjectList;
    }

    public static void updateSubject(String subjectID, String name, String teacherID, String studentID, String ocenyID) throws ClassNotFoundException {
        String updateStmt = "UPDATE subjects SET subject_name='" + name + "',id_teacher='" + teacherID + "',id_student='" + studentID + "',id_oceny='" + ocenyID + "'WHERE id_subject = '" + subjectID + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSubjectWithID(String subjectID) throws ClassNotFoundException {
        String updateStmt = "DELETE FROM subjects WHERE id_subject='" + subjectID + "'";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertSubject(String subjectID,String name, String teacherID, String studentID, String ocenyID) throws ClassNotFoundException {

        String updateStmt = "INSERT INTO subjects(id_subject,name, id_teacher, id_student, id_oceny) VALUES ('" + subjectID + "','"  + name + "','" + teacherID + "','" + studentID + "','" + ocenyID + "')";

        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//-----------------------------------------------TESTOWANIE----------------------------------------------

    /*public static ObservableList<Join> getObject() throws ClassNotFoundException, SQLException {

        String selectStmt = "SELECT students.id_student, students.name, students.surename, subjects.subject_name, marks.mark" +
                " FROM students" +
                " INNER JOIN subjects" +
                " ON students.id_subject = subjects.id_subject" +
                " INNER JOIN marks" +
                " ON subjects.id_oceny = marks.id_mark";

        try {
            ResultSet rs = MysqlConnection.dbExecuteQuery(selectStmt);

            ObservableList<Join> objectList = getObjectList(rs);

            return objectList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static ObservableList<Join> getObjectList(ResultSet rs) throws SQLException {
        ObservableList<Join> subjectList = FXCollections.observableArrayList();

        while (rs.next()) {
            Join join = new Join();

            join.setId(rs.getInt("id_student"));
            join.setName(rs.getString("name"));
            join.setSurename(rs.getString("surename"));
            join.setSubject(rs.getString("subject_name"));
            join.setMark(rs.getString("mark"));
            subjectList.add(join);

        }

        return subjectList;
    }*/

}
