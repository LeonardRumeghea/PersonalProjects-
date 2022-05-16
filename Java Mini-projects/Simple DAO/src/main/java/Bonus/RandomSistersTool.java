package Bonus;

import DAO.Cities;
import exceptions.NoCityFoundException;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RandomSistersTool {
    final List<String> cities = new ArrayList<>();
    Map<Integer, Integer> sisters = new HashMap<>();
    private final Cities cityDAO = new Cities();
    static int toBeGeneratedCities = 1000;
    static int toBeGeneratedRelationships = 20;
    int numberOfGeneratedCities = 0;
    int numberOfGeneratedRelationships = 0;
    final Lock lockCounter = new ReentrantLock();
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public void generateFakeSisterRelationships() {
        List<Future<?>> futures = new ArrayList<>();
        try {
            initialise();
            for (int i = 0; i < toBeGeneratedCities / 50 && numberOfGeneratedCities < toBeGeneratedCities; i++) {
                Future<?> future = executor.submit(new CityGenerator(this));
                futures.add(future);
            }
            for (int i = 0; i < toBeGeneratedRelationships / 10 && numberOfGeneratedRelationships < toBeGeneratedRelationships; i++) {
                Future<?> future = executor.submit(new SistersGenerator(this));
                futures.add(future);
            }

            for(Future<?> future : futures) {
                future.get();
            }

        } catch (NoCityFoundException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void initialise() throws NoCityFoundException {
        int numberOfCities = cityDAO.countCities();
        for(int id = 1; id <= numberOfCities; id++) {
            try {
                cities.add(cityDAO.findById(id).getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getCities() {
        return cities;
    }

    public Map<Integer, Integer> getSisters() {
        return sisters;
    }
}
