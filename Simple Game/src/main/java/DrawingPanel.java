import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingPanel extends JPanel {
    MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;

    private boolean repaintBoard = true;

    public GameBoard gameBoard;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init(frame.configPanel.getRows(), frame.configPanel.getCols());
    }

    final void init(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        gameBoard = new GameBoard(rows, cols, frame);
        repaintBoard = true;

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        paintGrid(g);

        if (repaintBoard) {
            gameBoard.generateRandomSticks();
        }

        drawSticks(g);

        g.setStroke(new BasicStroke(1));
        for (Rock rock : gameBoard.rocks) {
            rock.fill(g, Color.WHITE);
            rock.draw(g, Color.BLACK);
        }

        for (Rock rock : gameBoard.rocks) {

            if (!new Color(rock.getColor().getRed(), rock.getColor().getGreen(), rock.getColor().getBlue()).equals(Color.GRAY)) {
                rock.fill(g);
            }
        }

        if (repaintBoard) addEvents();

        repaintBoard = false;
    }

    void drawSticks(Graphics g) {
        for (Stick stick : gameBoard.sticks) stick.draw(g, Color.BLACK, new BasicStroke(4));
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);

        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }

        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int x2 = x1;
            int y2 = padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);

                Rock rock = new Rock(x - stoneSize / 2, y - stoneSize / 2, row, col);

                if (repaintBoard) {
                    gameBoard.rocks.add(rock);
                    gameBoard.addRockOnMap(rock, row, col);
                }
            }
        }
    }

    private void addEvents() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                for (Rock rock : gameBoard.rocks) {
                    if (rock.getShape().contains(e.getPoint()) && gameBoard.valid(rock)) {
                        gameBoard.setRock(rock);
                    }
                }
                repaint();
            }
        });
    }
}
