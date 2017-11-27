package Utils;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;
import java.util.Properties;

public class MysqlConnection {
    // tworzenie stałych bazy danych
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/school?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


    // inicjalizowanie obiektu connection
    private static Connection connection;
    private static Connection conn;
    // inicjalizowanie obiektu properties
    private static Properties properties;

    // Tworzenie danych logowania
    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);

        }
        return properties;
    }

    // Połączenie z bazą danych
    public static Connection Connector() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                System.out.println("Połączony z bazą danych");
            } catch (SQLException e) {
                DialogUtils.errorDialog(e.getMessage());
                return null;
            } catch (ClassNotFoundException e) {
                DialogUtils.errorDialog(e.getMessage());
                return null;
            }
        }
        return connection;
    }

    // Odłączenie bazy danych
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnection();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnection();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());

        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }

    //Connect to DB
    public static void dbConnection() throws ClassNotFoundException, SQLException {
        try{
            Class.forName(DATABASE_DRIVER);
        }catch (ClassNotFoundException e){
            DialogUtils.errorDialog(e.getMessage());
            System.out.println("Where is your Oracle JDBC Driver?");
            throw e;
        }

        System.out.println("Polączony z bazą danych");

        try{
            conn = DriverManager.getConnection(DATABASE_URL,getProperties());
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
            System.out.println("Connection Failed! Check output console");
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }
}