package While;

public class TrafficLightv2 {
    static void main() {
        final String R = "RED";
        final String Y = "YELLOW";
        final String G = "GREEN";

        String CurrentState = R;
        int cycles = 0;

        System.out.println("Traffic Light Simulator. Press Ctrl+C to stop");
        while (true) {
            cycles++;
            System.out.println("\n Cycle: " + cycles + " ---");

            switch (CurrentState) {
                case R:
                    System.out.println("Traffic Light in: " + R);
                    System.out.println("Stop");
                    CurrentState = G;
                    break;
                case G:
                    System.out.println("Traffic Light in: " + G);
                    System.out.println("Continue");
                    CurrentState = Y;
                    break;
                case Y:
                    System.out.println("Traffic Light in: " + Y);
                    System.out.println("Precaution");
                    CurrentState = R;
                    break;
            }

            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("The Simulation was interrupted");
                break;
            }
        }
    }
}
