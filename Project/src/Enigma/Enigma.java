package Enigma;

public class Enigma {
    private Rotor[] rotors;
    private Reflector reflector;
    private Plugboard plugboard;

    private static final String[] ROTOR_WIRINGS = {
            "EKMFLGDQVZNTOWYHXUSPAIBRCJ", // Rotor I
            "AJDKSIRUXBLHWTMCQGZNPYFVOE", // Rotor II
            "BDFHJLCPRTXVZNYEIWGAKMUSQO"  // Rotor III
    };

    private static final String REFLECTOR_WIRING = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

    public Enigma() {

        rotors = new Rotor[3];
        rotors[0] = new Rotor(ROTOR_WIRINGS[0], 'A', 'Q'); // Rotor rápido (derecha)
        rotors[1] = new Rotor(ROTOR_WIRINGS[1], 'A', 'E'); // Rotor medio
        rotors[2] = new Rotor(ROTOR_WIRINGS[2], 'A', 'V'); // Rotor lento (izquierda)

        reflector = new Reflector(REFLECTOR_WIRING);
        plugboard = new Plugboard();
    }

    public void setRotorPositions(char pos1, char pos2, char pos3) {
        rotors[0].setPosition(pos1);
        rotors[1].setPosition(pos2);
        rotors[2].setPosition(pos3);
    }

    public void setRingSettings(char ring1, char ring2, char ring3) {
        rotors[0].setRing(ring1);
        rotors[1].setRing(ring2);
        rotors[2].setRing(ring3);
    }

    public void setupPlugboard(String connections) {
        String[] pairs = connections.split(" ");
        for (String pair : pairs) {
            if (pair.length() == 2) {
                plugboard.connect(pair.charAt(0), pair.charAt(1));
            }
        }
    }

    public char encipherLetter(char letter) {
        letter = Character.toUpperCase(letter);
        rotateRotors();
        letter = plugboard.substitute(letter);

        for (int i = 0; i < 3; i++) {
            letter = rotors[i].encodeForward(letter);
        }

        letter = reflector.reflect(letter);

        for (int i = 2; i >= 0; i--) {
            letter = rotors[i].encodeBackward(letter);
        }

        letter = plugboard.substitute(letter);
        return letter;
    }

    public String encipherMessage(String message) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(encipherLetter(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    private void rotateRotors() {
        boolean rotorMiddleAtTurnover = rotors[0].isAtTurnover();
        boolean rotorLeftAtTurnover = rotors[1].isAtTurnover();

        if (rotorLeftAtTurnover) {
            rotors[2].rotate();
        }

        if (rotorMiddleAtTurnover) {
            rotors[1].rotate();
        }

        // Rotor derecho (siempre)
        // rotors[0].rotate();
    }

    public String getRotorPositions() {
        return String.format("Rotores: %c-%c-%c",
                rotors[2].getPosition(),
                rotors[1].getPosition(),
                rotors[0].getPosition());
    }

    public static void main(String[] args) {
        Enigma enigma = new Enigma();
        enigma.setRotorPositions('A', 'A', 'A');

        enigma.setRingSettings('A', 'A', 'A');

        enigma.setupPlugboard("AR GR"); // Intercambia A↔R y G↔R

        System.out.println("=== MÁQUINA ENIGMA ===\n");
        System.out.println("Posición inicial: " + enigma.getRotorPositions());
        System.out.println("Ring settings: AAA");
        System.out.println("Puntos de volteo históricos:");
        System.out.println("  - Rotor I (derecha): Q→R");
        System.out.println("  - Rotor II (medio): E→F");
        System.out.println("  - Rotor III (izquierda): V→W\n");

        for (int i = 0; i < 5; i++) {
            enigma4.encipherLetter('X');
            System.out.println("Después de cifrar: " + enigma4.getRotorPositions());
        }
    }
}
 