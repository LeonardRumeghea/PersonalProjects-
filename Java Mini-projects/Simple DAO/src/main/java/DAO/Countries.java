package DAO;

import Planet.Country;

import java.sql.*;

public class Countries {
    public void dropTable () throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE Countries");
        statement.executeUpdate("DROP SEQUENCE SEQ_COUNTRIES_ID");

        con.close();
    }

    public void createTable() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sqlQuery = "CREATE TABLE Countries (ID INT NOT NULL, name VARCHAR(64), code VARCHAR(4), continent INT, PRIMARY KEY (ID))";
        statement.executeUpdate(sqlQuery);

        sqlQuery = "CREATE SEQUENCE SEQ_COUNTRIES_ID START WITH 1 MAXVALUE 999999999 MINVALUE 1 CYCLE NOCACHE NOORDER";
        statement.executeUpdate(sqlQuery);

        sqlQuery =  "CREATE TRIGGER Countries_trigger BEFORE INSERT ON Countries " +
                "FOR EACH ROW " +
                "BEGIN " +
                "    SELECT SEQ_COUNTRIES_ID.nextval into :new.ID from dual; " +
                "END;";
        statement.executeUpdate(sqlQuery);

        con.close();
    }

    public void insert(String name, String code, int continentID) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Countries (name, code, continent) values (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, code);
            preparedStatement.setString(3, String.valueOf(continentID));
            preparedStatement.executeUpdate();
        }

        con.commit();
        con.close();
    }

    public boolean exit(String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Countries WHERE name='" + name + "'")) {

            boolean buffer = rs.next();
            con.close();
            return  buffer;
        }
    }

    public Planet.Country findByName(String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from Countries where name='" + name + "'")) {

            if (rs.next()) {
                Integer bufferId = Integer.valueOf(rs.getString("id"));
                String bufferCode = rs.getString("code");
                Integer bufferContinentId = rs.getInt("continent");

                con.close();
                return new Planet.Country(bufferId, name, bufferCode, bufferContinentId);
            }
            else {
                con.close();
                return new Planet.Country(null, null, null, null);
            }
        }
    }

    public Planet.Country findById(int id) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from Countries where id='" + id + "'")) {

            if (rs.next()) {
                String bufferName = rs.getString("name");
                String bufferCode = rs.getString("code");
                Integer bufferContinentId = rs.getInt("continent");

                con.close();
                return new Planet.Country(id, bufferName, bufferCode, bufferContinentId);
            }
            else {
                con.close();
                return new Planet.Country(null, null, null, null);
            }
        }
    }

    public void selectAll() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from Countries")) {

            System.out.println("Countries: ");
            while (rs.next()) {
                System.out.println("\tName: " + rs.getString("name") +
                        "\n\t\tContinent ID: " + rs.getString("continent") +
                        "\n\t\tCode: " + rs.getString("code"));
            }
        }
        con.close();
    }
}
