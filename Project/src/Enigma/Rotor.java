package Enigma;

class Rotor {
    private final String wiring;      // El cableado del rotor
    private int position;       // Posición actual (0-25)
    private final char turnoverPoint; // Punto en el que causa que el siguiente rotor gire
    private int ring;           // Posición del anillo (ring setting)

    // Configuración histórica de rotores Enigma
    // Cada rotor tiene su punto de volteo característico
    public Rotor(String wiring, char initialPosition, char turnover) {
        this.wiring = wiring;
        this.position = initialPosition - 'A';
        this.turnoverPoint = turnover;
        this.ring = 0; // Anillo en posición 'A' por defecto
    }

    public void setPosition(char pos) {
        this.position = pos - 'A';
    }

    public char getPosition() {
        return (char) ('A' + position);
    }

    /**
     * Configura la posición del anillo (ring setting)
     * Esto afecta el punto de volteo en 1 letra
     */
    public void setRing(char ringPos) {
        this.ring = ringPos - 'A';
    }

    public void rotate() {position = (position + 1) % 26;}

    public boolean isAtTurnover() {
        return getPosition() == turnoverPoint;
    }

    /**
     * Codifica una letra de derecha a izquierda (entrada normal)
     */
    public char encodeForward(char letter) {
        int input = letter - 'A';
        // Ajustar por la posición del rotor
        input = (input + position) % 26;
        // Pasar por el cableado
        char output = wiring.charAt(input);
        // Ajustar de vuelta por la posición
        int result = (output - 'A' - position) % 26;
        if (result < 0) result += 26;
        return (char) ('A' + result);
    }

    /**
     * Codifica una letra de izquierda a derecha (desde el reflector)
     */
    public char encodeBackward(char letter) {
        int input = letter - 'A';
        // Ajustar por la posición del rotor
        input = (input + position) % 26;
        // Encontrar qué entrada produce esta salida
        char output = 'A';
        for (int i = 0; i < 26; i++) {
            if (wiring.charAt(i) == (char) ('A' + input)) {
                output = (char) ('A' + i);
                break;
            }
        }
        // Ajustar de vuelta por la posición
        int result = (output - 'A' - position) % 26;
        if (result < 0) result += 26;
        return (char) ('A' + result);
    }
}