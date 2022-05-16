import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<String> words = new HashSet<>();

    public int addWord(String time, Player player, @NotNull String word) {

        int sum = 0;

        for(char c : word.toCharArray()) {
            sum += gameAlphabet.getAlphabetPoints().get(c);
        }

        words.add(word);
        System.out.println("\t" + time + " - " + player.getName() + " scribbled the word : " + word);

        return sum;
    }

    @Override
    public String toString() {
        return words.toString();
    }
}