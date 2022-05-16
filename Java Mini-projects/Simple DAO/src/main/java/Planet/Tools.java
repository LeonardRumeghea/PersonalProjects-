package Planet;

import java.util.List;

public class Tools {

    public static double distanceBetweenTwoCities(City firstCity, City secondCity) {
        double lon1 = Math.toRadians(firstCity.getLongitude());
        double lon2 = Math.toRadians(secondCity.getLongitude());
        double lat1 = Math.toRadians(firstCity.getLatitude());
        double lat2 = Math.toRadians(secondCity.getLatitude());

        double buffLon = lon2 - lon1;
        double buffLat = lat2 - lat1;
        double buff = Math.pow(Math.sin(buffLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(buffLon / 2),2);

        return(Math.asin(Math.sqrt(buff)) * 6371 * 2);
    }

    public static void displayWorldMap(List<City> cities) {
        new Map(cities).setVisible(true);
    }
}
