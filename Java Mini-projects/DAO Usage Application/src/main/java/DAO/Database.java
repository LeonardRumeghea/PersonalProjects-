package DAO;

import com.zaxxer.hikari.HikariConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        createConnection();
        return connection;
    }

    private static void createConnection() throws SQLException {

        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}