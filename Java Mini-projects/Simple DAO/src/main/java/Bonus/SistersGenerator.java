package Bonus;

import DAO.Cities;
import DAO.DatabaseHikari;
import exceptions.NoCityFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SistersGenerator implements Runnable {
    private final Cities cityDAO = new Cities();
    private final RandomSistersTool rst;

    public SistersGenerator(RandomSistersTool rst) {
        System.out.println("Hi there! New sister relationship thread out of: " + rst.executor.getActiveCount());
        this.rst = rst;
    }

    public boolean generateFakeRelationship() throws NoCityFoundException {
        synchronized (rst.lockCounter) {
            if (rst.numberOfGeneratedRelationships >= RandomSistersTool.toBeGeneratedRelationships) {
                return false;
            }
            rst.numberOfGeneratedRelationships++;
        }
        int numberOfCities = cityDAO.countCities();
        Random rand = new Random();
        int id1, id2;
        do {
            id1 = rand.nextInt(numberOfCities) + 1;
            id2 = rand.nextInt(numberOfCities) + 1;
        } while (id1 == id2 || (rst.sisters.containsKey(id1) && rst.sisters.get(id1) == id2) || (rst.sisters.containsKey(id2) && rst.sisters.get(id2) == id1));
        createSisterRelationship(id1, id2);
        return true;
    }

    private void createSisterRelationship(int id1, int id2) {
        try {
            Connection conn = DatabaseHikari.getDataSource().getConnection();
            try (PreparedStatement prepstmt = conn.prepareStatement("insert into sister_cities (id_city1, id_city2) values (?,?)")) {
                prepstmt.setInt(1, id1);
                prepstmt.setInt(2, id2);
                prepstmt.executeUpdate();
            }
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        synchronized (rst.sisters) {
            rst.sisters.put(id1, id2);
        }
    }

    @Override
    public void run() {
        try {
            boolean running;
            do {
                running = generateFakeRelationship();
            } while (running);
        } catch (NoCityFoundException e) {
            e.printStackTrace();
        }
    }
}
