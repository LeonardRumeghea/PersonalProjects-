import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton savePNGBtn = new JButton("Save as PNG");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        add(exitBtn);
        add(saveBtn);
        add(savePNGBtn);
        add(loadBtn);


        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveGame);
        savePNGBtn.addActionListener(this::savePNGGame);
        loadBtn.addActionListener(this::loadGame);

    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void saveGame(ActionEvent e) {
        GameBoard_Loader.saveAsXML(frame.canvas.gameBoard, "D:/gameBoard.xml");
    }

    private void loadGame(ActionEvent e) {
        //GameBoard_Loader.saveAsXML(frame.canvas.gameBoard, "D:/gameBoard.xml");
        frame.canvas.gameBoard = GameBoard_Loader.loadFromXML("D:/gameBoard.xml");
        frame.canvas.repaint();
    }

    public static BufferedImage getScreenShot(Component component) {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }

    private void savePNGGame(ActionEvent e) {
        BufferedImage img = getScreenShot(frame.canvas);

        JFileChooser fileChooser = new JFileChooser(".");
        JFrame choose = new JFrame();
        choose.setTitle("Save To ...");
        int status = fileChooser.showSaveDialog(choose);

        if (status == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();

                String newFile = selectedFile.getCanonicalPath();

                if (!newFile.endsWith(".png")) newFile += ".png";

                ImageIO.write(img, "png", new File(newFile));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
