import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private Dictionary dictionary;
    private final List<Player> players = new ArrayList<>();

    private int currentPlayer;
    public final Lock lock = new ReentrantLock();
    private int finishedPlayers;


    public Timer timer;

    Game() {
        try {
            dictionary = new Dictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer();
        Thread thread = new Thread(timer);
        thread.setDaemon(true);
        thread.start();
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }
    public void play() {
        currentPlayer = 1;
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public synchronized int getCurrentPlayer() {
        return currentPlayer;
    }

    public synchronized void increasePlayerNumber() {
        this.currentPlayer++;

        if (this.currentPlayer == players.size() + 1) {
            this.currentPlayer = 1;
        }
    }

    public void playerFinished() {
        finishedPlayers++;

        if (finishedPlayers == players.size()) {
            showWinner();
        }
    }

    private void showWinner() {

        players.forEach(player -> System.out.println("\t" + player.getName() + " collected " + player.getPointsCollected() + " points"));

        Player winner = players.stream().max(Comparator.comparingInt(Player::getPointsCollected)).orElse(null);

        System.out.println("Winner is: " + Objects.requireNonNull(winner).getName());
    }
}