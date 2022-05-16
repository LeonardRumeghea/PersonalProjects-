import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private String name;
    private Game game;
    private final int playerIndex;

    private int pointsCollected;

    public Player(String name, int index) {
        this.name = name;
        this.playerIndex = index;
    }

    private boolean submitWord() {

        synchronized (game.lock) {

            if (game.getBag().isEmpty()) {
                game.playerFinished();
                return false;
            }

            while (this.playerIndex != game.getCurrentPlayer()) {
                try {
                    game.lock.wait();
                    if (game.getBag().isEmpty()) {
                        game.playerFinished();
                        return false;
                    }

                } catch (InterruptedException e) { e.printStackTrace(); }
            }

            List<Tile> extracted = new ArrayList<>();
            int index = 7;

            int maxTimes = new Random().nextInt(2, 5);
            do {
                extracted.addAll(game.getBag().extractTiles(index));

                System.out.print("Before: ");
                extracted.forEach(tile -> System.out.print(tile.getLetter()));
                System.out.println();

                String myWord = game.getDictionary().getMyBestWord(extracted);

                if (myWord.compareTo("!null") == 0) {
                    maxTimes = 0;
                    extracted.clear();
                }
                else {
                    this.pointsCollected += game.getBoard().addWord(game.timer.toString(),this, myWord);

                    index = myWord.length();
                    List<Tile> buffer = new ArrayList<>();

                    for (Tile value : extracted) {
                        if (myWord.contains(String.valueOf(value.getLetter()))) {
                            myWord = myWord.replaceFirst(String.valueOf(value.getLetter()), "");
                        } else {
                            buffer.add(value);
                        }
                    }
                    extracted = buffer;

                    System.out.print("After: ");
                    extracted.forEach(tile -> System.out.print(tile.getLetter()));
                    System.out.println('\n');
                }

                try {
                    sleep(new Random().nextInt(500, 1000));

                } catch (InterruptedException e) { e.printStackTrace(); }

                maxTimes--;

            } while (maxTimes > 0);

            extracted.clear();

            game.increasePlayerNumber();
            game.lock.notifyAll();

            return true;
        }
    }

    @Override
    public void run() {

        while (submitWord());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPointsCollected() {
        return pointsCollected;
    }
}