import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Dictionary {

    public Dictionary() throws IOException {

        Trie.root = new Trie.TrieNode();

        BufferedReader reader = new BufferedReader(new FileReader("list_of_words_1.txt"));

        String word;
        while ((word = reader.readLine()) != null) {
            Trie.insert(word.toUpperCase(Locale.ROOT));
        }
    }

    public int getWordPoints(@NotNull String word) {
        int counter = 0;
        for (char c : word.toCharArray()) {
            counter += gameAlphabet.getAlphabetPoints().get(c);
        }

        return counter;
    }

    public String getMyBestWord(@NotNull List<Tile> extracted) {
        List<Character> letters = extracted.stream().map(Tile::getLetter).collect(Collectors.toList());

        return Trie.getPossibleWords(letters)
                .stream()
                .max(Comparator.comparingInt(this::getWordPoints))
                .orElse("!null");
    }
}