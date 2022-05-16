import java.util.HashMap;
import java.util.Map;

public class gameAlphabet {

    static Map<Character, Integer> getAlphabetPoints() {
        Map<Character, Integer> alphabet = new HashMap<>();

        alphabet.put('A', 1);
        alphabet.put('E', 1);
        alphabet.put('I', 1);
        alphabet.put('O', 1);
        alphabet.put('U', 1);
        alphabet.put('L', 1);
        alphabet.put('N', 1);
        alphabet.put('S', 1);
        alphabet.put('T', 1);
        alphabet.put('R', 1);
        alphabet.put('D', 2);
        alphabet.put('G', 2);
        alphabet.put('B', 3);
        alphabet.put('C', 3);
        alphabet.put('M', 3);
        alphabet.put('P', 3);
        alphabet.put('F', 4);
        alphabet.put('H', 4);
        alphabet.put('V', 4);
        alphabet.put('W', 4);
        alphabet.put('Y', 4);
        alphabet.put('K', 5);
        alphabet.put('J', 8);
        alphabet.put('X', 8);
        alphabet.put('Q', 10);
        alphabet.put('Z', 10);

        return alphabet;
    }

    static Map<Character, Integer> getAlphabetCount() {
        Map<Character, Integer> alphabet = new HashMap<>();

        alphabet.put('A', 9);
        alphabet.put('B', 2);
        alphabet.put('C', 2);
        alphabet.put('D', 4);
        alphabet.put('E', 12);
        alphabet.put('F', 2);
        alphabet.put('G', 3);
        alphabet.put('H', 2);
        alphabet.put('I', 9);
        alphabet.put('J', 1);
        alphabet.put('K', 1);
        alphabet.put('L', 4);
        alphabet.put('M', 2);
        alphabet.put('N', 6);
        alphabet.put('O', 8);
        alphabet.put('P', 2);
        alphabet.put('Q', 1);
        alphabet.put('R', 6);
        alphabet.put('S', 4);
        alphabet.put('T', 6);
        alphabet.put('U', 4);
        alphabet.put('V', 2);
        alphabet.put('W', 2);
        alphabet.put('X', 1);
        alphabet.put('Y', 2);
        alphabet.put('Z', 1);

        return alphabet;
    }
}
