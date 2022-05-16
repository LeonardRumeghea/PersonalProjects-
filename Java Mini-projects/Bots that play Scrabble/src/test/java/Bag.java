import java.util.*;

public class Bag {

    private final Queue<Tile> tiles;

    public Bag() {

        List<Tile> buffer = new ArrayList<>();

        Map<Character, Integer> alphabetCount = gameAlphabet.getAlphabetCount();
        Map<Character, Integer> alphabetPoints = gameAlphabet.getAlphabetPoints();

        for (char c = 'A'; c <= 'Z'; c++) {
            for (int index = 1; index <= alphabetCount.get(c); index++) {
                buffer.add(new Tile(c, alphabetPoints.get(c)));
            }
        }

        Collections.shuffle(buffer);
        tiles = new LinkedList<>(buffer);
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tiles.isEmpty()) {
                break;
            }
            extracted.add(tiles.poll());
        }
        return extracted;
    }

    public Boolean isEmpty() {
        return this.tiles.isEmpty();
    }
}