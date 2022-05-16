package Bonus;

import DAO.Cities;
import com.github.javafaker.Faker;

import java.sql.SQLException;

public class CityGenerator implements Runnable {
    private final Cities cityDAO = new Cities();
    private final RandomSistersTool rst;

    public CityGenerator(RandomSistersTool rst) {
        System.out.println("Hi there! New city thread out of: " + rst.executor.getActiveCount());
        this.rst = rst;
    }

    public boolean generateFakeCity() {
        synchronized (rst.lockCounter) {
            if (rst.numberOfGeneratedCities >= RandomSistersTool.toBeGeneratedCities) {
                return false;
            }
            rst.numberOfGeneratedCities++;
        }
        String nameOfCity;
        do {
            Faker faker = new Faker();
            nameOfCity = faker.address().cityName();
        } while (rst.cities.contains(nameOfCity));
        nameOfCity = nameOfCity.replace("'","`");
        try {
            cityDAO.insert(-1, nameOfCity, false, "0.0", "0.0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        synchronized (rst.cities) {
            rst.cities.add(nameOfCity);
        }
        return true;
    }

    @Override
    public void run() {
        boolean running;
        do {
            running = generateFakeCity();}
        while(running);
    }
}
