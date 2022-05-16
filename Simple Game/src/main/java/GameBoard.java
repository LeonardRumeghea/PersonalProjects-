import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

@XmlRootElement
public class GameBoard implements Serializable {

    private int currentPlayer = 0;
    private int rows;
    private int cols;
    private Rock[][] rocksMap;
    private Map<Rock, Boolean> haveStick;
    private Rock lastRockPlaced;

    public List<Stick> sticks;
    public List<Rock> rocks;

    MainFrame frame;

    GameBoard(int rows, int cols, MainFrame frame) {
        this.rows = rows;
        this.cols = cols;
        this.rocksMap = new Rock[rows][cols];
        this.haveStick = new HashMap<>();
        this.sticks = new ArrayList<>();
        this.rocks = new ArrayList<>();
        this.frame = frame;
        lastRockPlaced = null;
    }

    GameBoard() {}

    public void addRockOnMap(Rock rock, int row, int col) {
        this.rocksMap[row][col] = rock;
        this.haveStick.put(rock, false);
    }

    public void generateRandomSticks() {

        Random rnd = new Random();
        int stickCount = rnd.nextInt(rows * cols, 2 * rows * cols);

        int firstPosition = rnd.nextInt(rows);

        int secondPosition = rnd.nextInt(cols);
        while (firstPosition != secondPosition) secondPosition = rnd.nextInt(cols);

        for (int i = 1; i <= stickCount; i++) {
            int addRow, addCol = 0;

            if (firstPosition == 0) addRow = rnd.nextInt(0, 2);
            else if (firstPosition == rows - 1) addRow = rnd.nextInt(0, 2) - 1;
            else addRow = rnd.nextInt(-1, 2);

            if (addRow == 0) {
                if (secondPosition == 0) addCol = rnd.nextInt(0, 2);
                else if (secondPosition == cols - 1) addCol = rnd.nextInt(0, 2) - 1;
                else {
                    if (rnd.nextInt() % 2 == 0) addCol = -1;
                    else addCol = 1;
                }
            }

            this.sticks.add(new Stick(rocksMap[firstPosition][secondPosition], rocksMap[firstPosition + addRow][secondPosition + addCol]));
            haveStick.put(rocksMap[firstPosition][secondPosition], true);
            haveStick.put(rocksMap[firstPosition + addRow][secondPosition + addCol], true);

            firstPosition += addRow;
            secondPosition += addCol;
        }
    }

    public void setRock(Rock rock) {

        if (currentPlayer == 0) {
            rock.setColor(Color.BLUE);
            currentPlayer = 1;
        }
        else {
            rock.setColor(Color.RED);
            currentPlayer = 0;
        }

        if (haveWinner()) {
            frame.canvas.repaint();
            if (currentPlayer == 1) JOptionPane.showMessageDialog(null, "<html> The winner is the <font color=#0000FF> blue </font> player!");
            else JOptionPane.showMessageDialog(null, "<html> The winner is the <font color=#FF0000> red </font> player!");


            frame.canvas.init(frame.configPanel.getRows(), frame.configPanel.getCols());
        }
    }

    public boolean valid(@NotNull Rock rock) {

        //System.out.println(new Color(rock.getColor().getRed(), rock.getColor().getGreen(), rock.getColor().getBlue()).equals(Color.GRAY));

        if (!new Color(rock.getColor().getRed(), rock.getColor().getGreen(), rock.getColor().getBlue()).equals(Color.GRAY)) return false;

        if (!haveStick.get(rock)) return false;

        if (lastRockPlaced == null) {
            this.lastRockPlaced = rock;
            return true;
        }

        boolean ok = haveAStickBetween(rock, lastRockPlaced);

        if (!ok) return false;
        else {

            ok =    lastRockPlaced.getRowNumber() - 1 == rock.getRowNumber() && lastRockPlaced.getColNumber() == rock.getColNumber() ||
                    lastRockPlaced.getRowNumber() == rock.getRowNumber() && lastRockPlaced.getColNumber() + 1 == rock.getColNumber() ||
                    lastRockPlaced.getRowNumber() + 1 == rock.getRowNumber() && lastRockPlaced.getColNumber() == rock.getColNumber() ||
                    lastRockPlaced.getRowNumber() == rock.getRowNumber() && lastRockPlaced.getColNumber() - 1 == rock.getColNumber();

            if (ok) {
                lastRockPlaced = rock;
            }

            return ok;
        }
    }

    public boolean haveWinner() {

        Rock other;
        if (lastRockPlaced.getRowNumber() != 0) {
            other = rocksMap[lastRockPlaced.getRowNumber() - 1][lastRockPlaced.getColNumber()];
            if (haveAStickBetween(lastRockPlaced, other) && new Color(other.getColor().getRed(),other.getColor().getGreen(),other.getColor().getBlue()).equals(Color.GRAY)) {
                return false;
            }
        }

        if (lastRockPlaced.getRowNumber() != rows - 1) {
            other = rocksMap[lastRockPlaced.getRowNumber() + 1][lastRockPlaced.getColNumber()];
            if (haveAStickBetween(lastRockPlaced, other) && new Color(other.getColor().getRed(),other.getColor().getGreen(),other.getColor().getBlue()).equals(Color.GRAY)) {
                return false;
            }
        }

        if (lastRockPlaced.getColNumber() != 0) {
            other = rocksMap[lastRockPlaced.getRowNumber()][lastRockPlaced.getColNumber() - 1];
            if (haveAStickBetween(lastRockPlaced, other) && new Color(other.getColor().getRed(),other.getColor().getGreen(),other.getColor().getBlue()).equals(Color.GRAY)) {
                return false;
            }
        }

        if (lastRockPlaced.getColNumber() != cols - 1) {
            other = rocksMap[lastRockPlaced.getRowNumber()][lastRockPlaced.getColNumber() + 1];
            if (haveAStickBetween(lastRockPlaced, other) && new Color(other.getColor().getRed(),other.getColor().getGreen(),other.getColor().getBlue()).equals(Color.GRAY)) {
                return false;
            }
        }

        return true;
    }

    private boolean haveAStickBetween(@NotNull Rock rock1, @NotNull Rock rock2) {

        Color c1 = new Color(rock1.getColor().getRed(), rock1.getColor().getGreen(),rock1.getColor().getBlue());
        Color c2 = new Color(rock2.getColor().getRed(), rock2.getColor().getGreen(),rock2.getColor().getBlue());

        rock1.setColor(Color.GRAY);
        rock2.setColor(Color.GRAY);

        for (Stick stick : sticks) {
            if ((stick.getFirstNode() == rock2 && stick.getSecondNode() == rock1) || (stick.getFirstNode() == rock1 && stick.getSecondNode() == rock2)) {

                rock1.setColor(c1);
                rock2.setColor(c2);

                return true;
            }
        }

        rock1.setColor(c1);
        rock2.setColor(c2);

        return false;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public Rock[][] getRocksMap() {
        return rocksMap;
    }

    public void setRocksMap(Rock[][] rocksMap) {
        this.rocksMap = rocksMap;
    }

    public Map<Rock, Boolean> getHaveStick() {
        return haveStick;
    }

    public void setHaveStick(Map<Rock, Boolean> haveStick) {
        this.haveStick = haveStick;
    }

    public Rock getLastRockPlaced() {
        return lastRockPlaced;
    }

    public void setLastRockPlaced(Rock lastRockPlaced) {
        this.lastRockPlaced = lastRockPlaced;
    }

    @Override
    public String toString() {
        return "GameBoard{" +
                "currentPlayer=" + currentPlayer +
                ", rows=" + rows +
                ", cols=" + cols +
                ", rocksMap=" + Arrays.toString(rocksMap) +
                ", haveStick=" + haveStick +
                ", sticks=" + sticks +
                ", lastRockPlaced=" + lastRockPlaced +
                '}';
    }
}
