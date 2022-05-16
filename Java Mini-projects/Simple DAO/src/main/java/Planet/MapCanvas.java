package Planet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.tan;
import static java.lang.Math.log;

public class MapCanvas extends JPanel {
    int canvasWidth = 1205, canvasHeight = 1037;

    private final List<Pair> coords;

    final static double RADIUS_MAJOR = 6378137.0;
    final static double RADIUS_MINOR = 6356752.3142;

    public MapCanvas(List<City> cities) {
        coords = new ArrayList<>();
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        init(cities);
    }

    private void init(List<City> cities) {
        for(City city : cities) {
            double latitude = city.getLatitude();
            double longitude = city.getLongitude();

            double xValue = (longitude + 180) * (canvasWidth / 360.0);
            double latRad = latitude * PI / 180;

            double merN = log(tan((PI / 4) + (latRad / 2)));
            double yValue = (canvasHeight / 2.0) - (canvasWidth * merN / (2 * PI));

            coords.add(new Pair(xValue, yValue));
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/Planet/Mercator_Projection.svg.png"));
            g.drawImage(img, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Pair pair : coords) {

            g.setColor(Color.RED);
            g.fill(new Ellipse2D.Double(pair.firstValue - 5, pair.secondValue - 5, 10, 10));

            g.setStroke(new BasicStroke(2));
            g.setColor(Color.YELLOW);
            g.draw(new Ellipse2D.Double(pair.firstValue - 5, pair.secondValue - 5, 10, 10));
        }
    }
}
