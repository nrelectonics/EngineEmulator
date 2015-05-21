import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by laptop on 21.05.2015.
 */

public class EngineEmulator {

    public static void main (String[] args) {

        int dataBits = 0;
        int baudRate = 0;
        int stopBits = 0;
        int parity = 0;
        String portName = null;
        String command;
        Boolean exit = false;
        Scanner scanner;

        if (args.length > 0) {
            portName = args[0].toUpperCase();
            baudRate = Integer.parseInt(args[1]);
            stopBits = Integer.parseInt(args[2]);
            parity = Integer.parseInt(args[3]);
        }

        System.out.println("Engine emulator. Version 0.01.");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable terminal = new DevTerminal(portName, baudRate, dataBits, stopBits, parity);
        executor.execute(terminal);

        scanner = new Scanner(System.in);
        while (!exit) {
            switch (command = scanner.nextLine()) {
                case "sd":
                    System.out.println("Execute SHUTDOWN command.");
                    exit = true;
                    break;
                default:
                    break;
            }
        }
        System.out.println("asdasd");
        executor.shutdown();
    }
}
