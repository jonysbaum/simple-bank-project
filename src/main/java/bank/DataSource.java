package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    public static Connection connect() throws SQLException {
        String db_file = "jdbc:sqlite:resources/bank.db";
        Connection connection = DriverManager.getConnection(db_file);

        try {
            connection = DriverManager.getConnection(db_file);
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    public static void main(String[] args) throws SQLException {
        connect();
    }
}
