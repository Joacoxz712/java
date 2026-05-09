package Enigma;

class Rotor {
    private final String wiring;
    private int position;
    private final char turnoverPoint;
    private int ring;

    public Rotor(String wiring, char initialPosition, char turnover) {
        this.wiring = wiring;
        this.position = initialPosition - 'A';
        this.turnoverPoint = turnover;
        this.ring = 0;
    }

    public void setPosition(char pos) {
        this.position = pos - 'A';
    }
    public char getPosition() {
        return (char) ('A' + position);
    }

    public void setRing(char ringPos) {
        this.ring = ringPos - 'A';
    }

    public void rotate() {position = (position + 1) % 26;}

    public boolean isAtTurnover() {
        return getPosition() == turnoverPoint;
    }

    public char encodeForward(char letter) {
        int input = letter - 'A';
        input = (input + position) % 26;

        char output = wiring.charAt(input);
        int result = (output - 'A' - position) % 26;
        if (result < 0) result += 26;
        return (char) ('A' + result);
    }

    public char encodeBackward(char letter) {
        int input = letter - 'A';
        input = (input + position) % 26;

        char output = 'A';
        for (int i = 0; i < 26; i++) {
            if (wiring.charAt(i) == (char) ('A' + input)) {
                output = (char) ('A' + i);
                break;
            }
        }
        int result = (output - 'A' - position) % 26;
        if (result < 0) result += 26;
        return (char) ('A' + result);
    }
}