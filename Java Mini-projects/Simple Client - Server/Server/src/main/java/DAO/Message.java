package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Message {
    public void dropTable () throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE Messages");
        statement.executeUpdate("DROP SEQUENCE SEQ_MESSAGES_ID");

        con.close();
    }

    public void createTable() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sqlQuery = "CREATE TABLE Messages ( " +
                "ID INT NOT NULL, " +
                "fromUser VARCHAR(64) NOT NULL, " +
                "toUser VARCHAR(64) NOT NULL, " +
                "content VARCHAR(512) NOT NULL, " +
                "read CHAR(1) DEFAULT 'N' )";

        statement.executeUpdate(sqlQuery);

        sqlQuery = "CREATE SEQUENCE SEQ_MESSAGES_ID START WITH 1 MAXVALUE 999999999 MINVALUE 1 CYCLE NOCACHE NOORDER";
        statement.executeUpdate(sqlQuery);

        sqlQuery =  "CREATE TRIGGER Messages_trigger BEFORE INSERT ON Messages " +
                "FOR EACH ROW " +
                "BEGIN " +
                "    SELECT SEQ_MESSAGES_ID.nextval into :new.ID from dual; " +
                "END;";
        statement.executeUpdate(sqlQuery);

        con.close();
    }

    public void insert(Integer from, Integer to, String content) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Messages (fromUser, toUser, content, read) values (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            preparedStatement.setString(3, content);
            preparedStatement.setString(4, "N");

            preparedStatement.executeUpdate();
        }
        con.commit();
        con.close();
    }

    public List<Utility.Message> getUnreadMessages(Integer byUser) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        List<Utility.Message> buffer = new ArrayList<>();

        try (Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM Messages WHERE toUser = '" + byUser + "' AND read = 'N'")) {

            while (rs.next()) {

                buffer.add(new Utility.Message(
                        rs.getString("fromUser"),
                        rs.getString("toUser"),
                        rs.getString("content")
                ));
            }
        }

        Statement statement = con.createStatement();
        String query = "UPDATE Messages SET read = 'Y' WHERE read = 'N' AND TOUSER = " + byUser;
        statement.executeUpdate(query);

        con.commit();
        con.close();
        return buffer;
    }
    public List<Utility.Message> getAllMessages(Integer byUser) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        List<Utility.Message> buffer = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Messages WHERE toUser = '" + byUser + "'")) {

            while (rs.next()) {
                buffer.add(new Utility.Message(
                        rs.getString("fromUser"),
                        rs.getString("toUser"),
                        rs.getString("content")
                ));
            }
        }

        Statement statement = con.createStatement();
        String query = "UPDATE Messages SET read = 'Y' WHERE TOUSER = " + byUser;
        statement.executeUpdate(query);

        con.close();
        return buffer;
    }
}
