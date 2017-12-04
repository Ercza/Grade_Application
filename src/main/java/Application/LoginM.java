package Application;

import Utils.MysqlConnection;
import Utils.DialogUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginM {

    Connection connection = null;

    public LoginM() {
        this.connection = new MysqlConnection().Connector();
        if (this.connection == null) {
            System.out.println("Połączenie nieudane!");
            System.exit(1);
        }
    }

    public boolean isDbConnected() { //funkcja sprawdzająca czy sie połączyliśmy z bazą danych
        try {
            return !this.connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin(String user, String pass, String option) throws SQLException { //funkcja sprawdzająca czy sie zalogowalismy
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Select * from Login Where username = ? and password = ? and partof = ?";
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, option);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}
