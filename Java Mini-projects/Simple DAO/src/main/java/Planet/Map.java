package Planet;

import javax.swing.*;
import static java.awt.BorderLayout.*;
import java.util.List;

public class Map extends JFrame {

    MapCanvas canvas;

    public Map(List<City> cities) {
        super("Map");
        init(cities);
    }

    private void init(List<City> cities) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new MapCanvas(cities);
        add(canvas, CENTER);
        pack();
    }
}
