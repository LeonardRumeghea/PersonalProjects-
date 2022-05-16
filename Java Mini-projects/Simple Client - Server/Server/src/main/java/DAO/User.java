package DAO;

import java.sql.*;

public class User {
    public void dropTable () throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE Users");
        statement.executeUpdate("DROP SEQUENCE SEQ_USERS_ID");

        con.close();
    }

    public void createTable() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sqlQuery = "CREATE TABLE Users ( " +
                "ID INT NOT NULL, " +
                "name VARCHAR(64) NOT NULL, " +
                "logged CHAR(1) DEFAULT 'N', " +
                "CONSTRAINT supplier_unique UNIQUE (name) )";

        statement.executeUpdate(sqlQuery);

        sqlQuery = "CREATE SEQUENCE SEQ_USERS_ID START WITH 1 MAXVALUE 999999999 MINVALUE 1 CYCLE NOCACHE NOORDER";
        statement.executeUpdate(sqlQuery);

        sqlQuery =  "CREATE TRIGGER Users_trigger BEFORE INSERT ON Users " +
                "FOR EACH ROW " +
                "BEGIN " +
                "    SELECT SEQ_USERS_ID.nextval into :new.ID from dual; " +
                "END;";
        statement.executeUpdate(sqlQuery);

        con.close();
    }

    public void insert(String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Users (name, logged) values (?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, "Y");

            preparedStatement.executeUpdate();
        }
        con.commit();
        con.close();
    }

    public boolean exits (String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE name='" + name + "'")) {

            boolean buffer = rs.next();
            con.close();
            return  buffer;
        }
    }

    public Utility.User findByName(String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE name='" + name + "'")) {

            if (rs.next()) {
                Integer bufferId = rs.getInt("id");

                con.close();
                return new Utility.User(name, bufferId);
            }
            else {
                con.close();
                return new Utility.User(null, null);
            }
        }
    }

    public Utility.User findById(int id) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from Users where id='" + id + "'")) {

            if (rs.next()) {
                String bufferName = rs.getString("name");
                con.close();
                return new Utility.User(bufferName, id);
            }
            else {
                con.close();
                return new Utility.User(null, null);
            }
        }
    }
}
