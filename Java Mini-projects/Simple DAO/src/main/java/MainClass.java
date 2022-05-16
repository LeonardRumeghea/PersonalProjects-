import DAO.*;
import Planet.Tools;
import Bonus.*;
import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Set;

public class MainClass {

    public static void main(String[] args) {

        //Compulsory();

//        DeleteData();
//        LoadDataCapitals();

        Homework();
    }

    static void LoadDataCapitals() {

        Continent continent = new Continent();
        Countries countries = new Countries();
        Cities cities = new Cities();

        try {
            continent.createTable();
            countries.createTable();
            cities.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String line;
        //    0-CountryName    1-CapitalName    2-CapitalLatitude    3-CapitalLongitude    4-CountryCode    5-ContinentName
        try {
            BufferedReader reader = new BufferedReader(new FileReader("concap.csv"));

            while ((line = reader.readLine()) != null) {
                String[] buffer = line.split(",");

                if (!continent.exit(buffer[5])) {
                    continent.insert(buffer[5]);
                }

                if (!countries.exit(buffer[0])) {
                    countries.insert(buffer[0], buffer[4], continent.findByName(buffer[5]).getId());
                }

                if (!cities.exit(buffer[1])) {
                    cities.insert(countries.findByName(buffer[0]).getId(), buffer[1], true, buffer[2], buffer[3]);
                }

                cities.printAll();
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    static void DeleteData() {

        Continent continent = new Continent();
        Countries countries = new Countries();
        Cities cities = new Cities();

        try {
            continent.dropTable();
            countries.dropTable();
            cities.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void Homework(){

        DAO.Cities cities = new DAO.Cities();

        try {

            DecimalFormat df = new DecimalFormat("0.00");

            System.out.println("From Bucharest to Doha are " +
                    df.format(Planet.Tools.distanceBetweenTwoCities(
                            cities.findByName("Bucharest"),
                            cities.findByName("Doha")))
                    + " km");

            Tools.displayWorldMap(cities.getAll());
//Bonus:
            RandomSistersTool randomSisters = new RandomSistersTool(); //using Thread Executor
            randomSisters.generateFakeSisterRelationships();
            System.out.println("\n-----Maximal cliques generation started-----\n");

            System.out.println("Numar de orase: " + randomSisters.getCities().size());
            System.out.println("Numar de relatii de \"suroritate:\" " + randomSisters.getSisters().size());
            printAllMaximalCliques(randomSisters);

            //cities.getAll().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void printAllMaximalCliques(RandomSistersTool randomSisters) {
        BronKerbosch bk = new BronKerbosch(randomSisters);
        Collection<Set<String>> result = bk.getBiggestMaximalCliques();
        for(Set<String> rs : result) {
            System.out.print("Clică maximală de orașe-surori: ");
            for(String city : rs) {
                System.out.print(" - " + city + " - ");
            }
            System.out.print("\n");
        }
    }
}
