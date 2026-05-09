package While;

public class Counter {
    public static void main(String[] args) {
        int counter = 1;
        while (counter <= 5) {
            System.out.println("Number: " + counter);
            counter++;
        }
        System.out.println("Finished loop");
    }
}
