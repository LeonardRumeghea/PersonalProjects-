package DAO;

import exceptions.NoCityFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cities {

    public void dropTable () throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE Cities");
        statement.executeUpdate("DROP SEQUENCE SEQ_CITIES_ID");

        con.close();
    }

    public void createTable() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sqlQuery = "CREATE TABLE Cities ( ID INT NOT NULL, country INT, " +
                                                "name VARCHAR(64), " +
                                                "capital char(1) DEFAULT 'N', " +
                                                "latitude VARCHAR(32), " +
                                                "longitude VARCHAR(32), " +
                                                "PRIMARY KEY (ID))";
        statement.executeUpdate(sqlQuery);

        sqlQuery = "CREATE SEQUENCE SEQ_CITIES_ID START WITH 1 MAXVALUE 999999999 MINVALUE 1 CYCLE NOCACHE NOORDER";
        statement.executeUpdate(sqlQuery);

        sqlQuery =  "CREATE TRIGGER Cities_trigger BEFORE INSERT ON Cities " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "    SELECT SEQ_CITIES_ID.nextval into :new.ID from dual; " +
                    "END;";
        statement.executeUpdate(sqlQuery);

        con.close();
    }

    public void insert(int country, String name, Boolean capital, String latitude, String longitude) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Cities (country, name, capital, latitude, longitude) values (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, String.valueOf(country));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, (capital ? "Y" : "N"));
            preparedStatement.setString(4, latitude);
            preparedStatement.setString(5, longitude);

            preparedStatement.executeUpdate();
        }
        con.commit();
        con.close();
    }

    public boolean exit(String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Cities WHERE name='" + name + "'")) {

            boolean buffer = rs.next();
            con.close();
            return  buffer;
        }
    }

    public Planet.City findByName(String name) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Cities WHERE name='" + name + "'")) {

            if (rs.next()) {
                Integer bufferId = rs.getInt("id");
                Integer bufferCountryId = rs.getInt("country");
                String bufferCapital = rs.getString("capital");
                float bufferLatitude = rs.getFloat("latitude");
                float bufferLongitude = rs.getFloat("longitude");

                con.close();
                return new Planet.City(bufferId, name, bufferCountryId, bufferCapital.equals("Y"), bufferLatitude, bufferLongitude);
            }
            else {
                con.close();
                return new Planet.City(null, "", null, false, -1, -1);
            }
        }
    }

    public Planet.City findById(int id) throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from Cities where id='" + id + "'")) {

            if (rs.next()) {
                String bufferName = rs.getString("name");
                Integer bufferCountryId = rs.getInt("country");
                String bufferCapital = rs.getString("capital");
                float bufferLatitude = rs.getFloat("latitude");
                float bufferLongitude = rs.getFloat("longitude");
                con.close();
                return new Planet.City(id, bufferName, bufferCountryId, bufferCapital.equals("Y"), bufferLatitude, bufferLongitude);
            }
            else {
                con.close();
                return new Planet.City(null, "", null, false, -1, -1);
            }
        }
    }

    public int countCities() throws NoCityFoundException {
        //Connection con = Database.getConnection();
        try {
            int noCities;
            Connection con = DatabaseHikari.getDataSource().getConnection();
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "select count(*) from cities")) {
                if (rs.next() && rs.getInt(1) != 0) {
                    noCities = rs.getInt(1);
                } else {
                    throw new NoCityFoundException();
                }
            }
            con.close();
            return noCities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoCityFoundException();
    }

    public void  printAll() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from Cities")) {
            System.out.println("Cities: ");
            while (rs.next()) {
                System.out.println("\tName: " + rs.getString("name") +
                        "\n\t\tCountry ID: " + rs.getString("country") +
                        "\n\t\tCapital: " + rs.getString("capital") +
                        "\n\t\tLatitude: " + rs.getString("latitude") +
                        "\n\t\tLongitude: " + rs.getString("longitude"));
            }
        }
        con.close();
    }

    public List<Planet.City> getAll() throws SQLException {
        Connection con = DatabaseHikari.getDataSource().getConnection();
        List<Planet.City> buffer = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("select * from Cities")) {

            while (rs.next()) {

                buffer.add(new Planet.City(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("country"),
                        rs.getString("capital").equals("Y"),
                        rs.getFloat("latitude"),
                        rs.getFloat("longitude")));
            }
        }
        con.close();

        return  buffer;
    }
}
