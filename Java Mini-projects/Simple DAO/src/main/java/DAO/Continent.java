package DAO;

import javax.sql.DataSource;
import java.sql.*;

public class Continent {

    public void dropTable () throws SQLException {

        DataSource dataSource = DatabaseHikari.getDataSource();
        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE Continents");
        statement.executeUpdate("DROP SEQUENCE SEQ_CONTINENT_ID");

        //Database.closeConnection();
        con.close();
    }

    public void createTable() throws SQLException {

        DataSource dataSource = DatabaseHikari.getDataSource();
        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        String sqlQuery = "CREATE TABLE Continents (ID int NOT NULL, NAME varchar(32), PRIMARY KEY (ID))";
        statement.executeUpdate(sqlQuery);

        sqlQuery = "CREATE SEQUENCE SEQ_CONTINENT_ID START WITH 1 MAXVALUE 999999999 MINVALUE 1 CYCLE NOCACHE NOORDER";
        statement.executeUpdate(sqlQuery);

        sqlQuery =  "CREATE TRIGGER Continents_trigger BEFORE INSERT ON Continents " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "    SELECT SEQ_CONTINENT_ID.nextval into :new.ID from dual; " +
                    "END;";
        statement.executeUpdate(sqlQuery);

        con.close();
    }

    public void insert(String name) throws SQLException {

        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Continents (name) values (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            con.commit();
        }

        con.close();
    }

    public boolean exit(String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Continents WHERE name='" + name + "'")) {

            boolean buffer = rs.next();
            con.close();
            return  buffer;
        }
    }

    public Planet.Continent findByName(String name) throws SQLException {
        //Connection con = Database.getConnection();

        DataSource dataSource = DatabaseHikari.getDataSource();
        Connection con = dataSource.getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select id from continents where name='" + name + "'")) {
            Integer buffer = rs.next() ? rs.getInt(1) : null;

            con.close();
            return (new Planet.Continent(buffer, name));
        }
    }

    public Planet.Continent findById(int id) throws SQLException {

        DataSource dataSource = DatabaseHikari.getDataSource();
        Connection con = dataSource.getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select name from continents where id='" + id + "'")) {

            String buffer =  rs.next() ? rs.getString(1) : null;
            con.close();
            return (new Planet.Continent(id, buffer));
        }
    }

    public void selectAll() throws SQLException {

        DataSource dataSource = DatabaseHikari.getDataSource();
        Connection con = dataSource.getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from continents")) {
                System.out.println("ID\tNAME");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
                }
        }

        con.close();
    }
}
