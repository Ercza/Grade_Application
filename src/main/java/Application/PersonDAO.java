package Application;


import Utils.DialogUtils;
import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO {
    //SELECT an Employee DZIALA
    public static Person searchPerson(String personId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM persons WHERE PERSON_ID=" + personId;
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPerson = MysqlConnection.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Person person = getPersonFromResultSet(rsPerson);
            //Return employee object
            return person;
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
            //Return exception
            throw e;
        }
    }

    //UPDATE person's email address DZIALA
    public static void updatePersonDetails(String personId, String personEmail, String personName, String personSurename) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement

        String updateStmt = "UPDATE persons SET EMAIL='" + personEmail + "',FIRST_NAME='"+ personName + "',LAST_NAME='"+ personSurename + "'WHERE PERSON_ID = " + personId;

        //Execute UPDATE operation
        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    //DELETE person DZIALA
    public static void deletePersonWithId(String personId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM persons WHERE PERSON_ID= " + personId;

        //Execute UPDATE operation
        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    //INSERT person DZIALA
    public static void insertPerson(String name, String lastname, String email) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "INSERT INTO persons(FIRST_NAME, LAST_NAME, EMAIL) VALUES ('" + name + "','" + lastname + "','" + email + "')";

        //Execute DELETE operation
        try {
            MysqlConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static Person getPersonFromResultSet(ResultSet rs) throws SQLException {
        Person person = null;
        if (rs.next()) {
            person = new Person();
            person.setPerson_id(rs.getInt("PERSON_ID"));
            person.setFirstName(rs.getString("FIRST_NAME"));
            person.setLastName(rs.getString("LAST_NAME"));
            person.setEmail(rs.getString("EMAIL"));
        }
        return person;
    }

    //SELECT Persons
    public static ObservableList<Person> searchPersons() throws ClassNotFoundException, SQLException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM persons";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPerson = MysqlConnection.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Person> personList = getPersonList(rsPerson);

            //Return person object
            return personList;
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
            //Return exception
            throw e;
        }
    }

    //Select * from persons operation
    private static ObservableList<Person> getPersonList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Person objects
        ObservableList<Person> personList = FXCollections.observableArrayList();

        while (rs.next()) {
            Person person = new Person();
            person.setPerson_id(rs.getInt("PERSON_ID"));
            person.setFirstName(rs.getString("FIRST_NAME"));
            person.setLastName(rs.getString("LAST_NAME"));
            person.setEmail(rs.getString("EMAIL"));
            //Add person to the ObservableList
            personList.add(person);
        }
        //return personList (ObservableList of Persons)
        return personList;
    }

}
