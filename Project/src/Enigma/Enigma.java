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

    /**
     * Configura la posición inicial de los rotores
     * Ejemplo: setRotorPositions('A', 'A', 'A')
     */
    public void setRotorPositions(char pos1, char pos2, char pos3) {
        rotors[0].setPosition(pos1);
        rotors[1].setPosition(pos2);
        rotors[2].setPosition(pos3);
    }

    /**
     * Configura los ring settings (posiciones del anillo)
     * Afecta dónde ocurre exactamente el volteo
     * Ejemplo: setRingSettings('A', 'A', 'A')
     */
    public void setRingSettings(char ring1, char ring2, char ring3) {
        rotors[0].setRing(ring1);
        rotors[1].setRing(ring2);
        rotors[2].setRing(ring3);
    }

    /**
     * Configura las conexiones del panel de conexión
     * Ejemplo: plugboard.connect('A', 'B') intercambia A y B
     */
    public void setupPlugboard(String connections) {
        // connections formato: "AB CD EF..." (pares de letras a intercambiar)
        String[] pairs = connections.split(" ");
        for (String pair : pairs) {
            if (pair.length() == 2) {
                plugboard.connect(pair.charAt(0), pair.charAt(1));
            }
        }
    }

    /**
     * Cifra una única letra
     */
    public char encipherLetter(char letter) {
        letter = Character.toUpperCase(letter);

        // 1. Rotar los rotores (antes de procesar la letra)
        rotateRotors();

        // 2. Panel de conexión (entrada)
        letter = plugboard.substitute(letter);

        // 3. Pasar por los rotores (derecha a izquierda)
        for (int i = 0; i < 3; i++) {
            letter = rotors[i].encodeForward(letter);
        }

        // 4. Reflector
        letter = reflector.reflect(letter);

        // 5. Pasar por los rotores en inverso (izquierda a derecha)
        for (int i = 2; i >= 0; i--) {
            letter = rotors[i].encodeBackward(letter);
        }

        // 6. Panel de conexión (salida)
        letter = plugboard.substitute(letter);

        return letter;
    }

    /**
     * Cifra un mensaje completo
     */
    public String encipherMessage(String message) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(encipherLetter(c));
            } else {
                result.append(c); // Preserva espacios y puntuación
            }
        }
        return result.toString();
    }

    /**
     * Rota los rotores (mecanismo de avance)
     * El rotor derecho avanza cada letra
     * El rotor medio avanza cuando el derecho pasa de Z a A
     * El rotor izquierdo avanza cuando el medio pasa de Z a A
     */
    private void rotateRotors() {
        // Detectar si los rotores medio e izquierdo deben avanzar
        boolean rotorMiddleAtTurnover = rotors[0].isAtTurnover();
        boolean rotorLeftAtTurnover = rotors[1].isAtTurnover();

        // Rotor izquierdo (más lento)
        if (rotorLeftAtTurnover) {
            rotors[2].rotate();
        }

        // Rotor medio
        if (rotorMiddleAtTurnover) {
            rotors[1].rotate();
        }

        // Rotor derecho (siempre)
        // rotors[0].rotate();
    }

    /**
     * Muestra el estado actual de los rotores
     */
    public String getRotorPositions() {
        return String.format("Rotores: %c-%c-%c",
                rotors[2].getPosition(),
                rotors[1].getPosition(),
                rotors[0].getPosition());
    }

    public static void main(String[] args) {
        Enigma enigma = new Enigma();

        // Configurar posiciones iniciales
        enigma.setRotorPositions('A', 'A', 'A');

        // Configurar ring settings (anillos)
        enigma.setRingSettings('A', 'A', 'A');

        // Configurar panel de conexión (opcional)
        enigma.setupPlugboard("AR GR"); // Intercambia A↔R y G↔R

        System.out.println("=== MÁQUINA ENIGMA ===\n");
        System.out.println("Posición inicial: " + enigma.getRotorPositions());
        System.out.println("Ring settings: AAA");
        System.out.println("Puntos de volteo históricos:");
        System.out.println("  - Rotor I (derecha): Q→R");
        System.out.println("  - Rotor II (medio): E→F");
        System.out.println("  - Rotor III (izquierda): V→W\n");

        // Ejemplo 1: Cifrar una palabra
        String plaintext = "HELLO";
        String ciphertext = enigma.encipherMessage(plaintext);
        System.out.println("Texto plano: " + plaintext);
        System.out.println("Texto cifrado: " + ciphertext);
        System.out.println(enigma.getRotorPositions());

        // Ejemplo 2: Descifrar (reciprocidad de Enigma)
        // Reseteamos y ciframos nuevamente con las mismas posiciones
        Enigma enigma2 = new Enigma();
        enigma2.setRotorPositions('A', 'A', 'A');
        enigma2.setRingSettings('A', 'A', 'A');
        enigma2.setupPlugboard("AR GR");

        String decrypted = enigma2.encipherMessage(ciphertext);
        System.out.println("\nCifrado: " + ciphertext);
        System.out.println("Descifrado: " + decrypted);
        System.out.println("¿Coincide? " + plaintext.equals(decrypted));

        // Ejemplo 3: Demostrar que la misma letra se cifra diferente
        System.out.println("\n--- Efecto de rotación ---");
        Enigma enigma3 = new Enigma();
        enigma3.setRotorPositions('A', 'A', 'A');
        enigma3.setRingSettings('A', 'A', 'A');
        System.out.println("Posición: " + enigma3.getRotorPositions());
        System.out.println("Primera 'A': " + enigma3.encipherLetter('A'));
        System.out.println("Segunda 'A': " + enigma3.encipherLetter('A'));
        System.out.println("Tercera 'A': " + enigma3.encipherLetter('A'));

        // Ejemplo 4: Demostración de punto de volteo
        System.out.println("\n--- Demostración de volteo (Rotor I en Q→R) ---");
        Enigma enigma4 = new Enigma();
        enigma4.setRotorPositions('P', 'A', 'A');
        enigma4.setRingSettings('A', 'A', 'A');
        System.out.println("Posición inicial: " + enigma4.getRotorPositions());

        for (int i = 0; i < 5; i++) {
            enigma4.encipherLetter('X');
            System.out.println("Después de cifrar: " + enigma4.getRotorPositions());
        }
    }
}
 