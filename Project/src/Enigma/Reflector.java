package Enigma;

public class Reflector {
    private final String wiring;

    public Reflector(String wiring) {
        this.wiring = wiring;
    }

    public char reflect(char let) {
        int i = let - 'A';
        return wiring.charAt(i);
    }
}
