package Enigma;

import java.util.HashMap;
import java.util.Map;

public class Plugboard {
    private final Map<Character, Character> connections;

    public Plugboard() {
        connections = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            connections.put(c, c);
        }
    }

    public void connect(char a, char b) {
        connections.put(a, b);
        connections.put(b, a);
    }

    public char substitute(char let) {
        return connections.getOrDefault(let, let);
    }
}
