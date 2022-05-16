package DAO;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Friendship {

    public void dropTable () throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE Friendship");
        statement.executeUpdate("DROP SEQUENCE SEQ_FRIENDSHIPS_ID");

        con.close();
    }

    public void createTable() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sqlQuery = "CREATE TABLE Friendships ( " +
                "ID INT NOT NULL, " +
                "user_1 INT NOT NULL, " +
                "user_2 INT NOT NULL )";

        statement.executeUpdate(sqlQuery);

        sqlQuery = "CREATE SEQUENCE SEQ_FRIENDSHIPS_ID START WITH 1 MAXVALUE 999999999 MINVALUE 1 CYCLE NOCACHE NOORDER";
        statement.executeUpdate(sqlQuery);

        sqlQuery =  "CREATE TRIGGER Friendships_trigger BEFORE INSERT ON Friendships " +
                "FOR EACH ROW " +
                "BEGIN " +
                "    SELECT SEQ_FRIENDSHIPS_ID.nextval into :new.ID from dual; " +
                "END;";
        statement.executeUpdate(sqlQuery);

        con.close();
    }

    public void insert(Utility.@NotNull User firstUser, Utility.@NotNull User secondUser) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Friendships (user_1, user_2) values (?, ?)")) {
            preparedStatement.setInt(1, firstUser.getId());
            preparedStatement.setInt(2, secondUser.getId());

            preparedStatement.executeUpdate();
        }
        con.commit();
        con.close();
    }

    public List<Utility.User> getFriends(Integer ofUser) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        List<Utility.User> buffer = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Friendships WHERE user_1 = '" + ofUser + "'")) {

            while (rs.next()) {
                buffer.add((new DAO.User()).findById(rs.getInt("user_1")));
            }
        }

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Friendships WHERE user_2 = '" + ofUser + "'")) {

            while (rs.next()) {
                buffer.add((new DAO.User()).findById(rs.getInt("user_1")));
            }
        }

        con.close();
        return buffer;
    }
}
