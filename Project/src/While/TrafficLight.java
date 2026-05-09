package While;

public class TrafficLight {
    public static void main(String[] args) {
        int time = 10;

        System.out.println("Traffic Light is green!");

        while (time > 0) {
            System.out.println("Remaining time in green: " + time + " secs");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }
        System.out.println("Traffic Light is changed to Red");
    }
}
