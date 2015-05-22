import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.Scanner;

public class EngineEmulator {

    public static volatile SerialPort serialPort;

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
            dataBits = Integer.parseInt(args[2]);
            stopBits = Integer.parseInt(args[3]);
            parity = Integer.parseInt(args[4]);
        }

        System.out.println("Engine emulator. Version 0.01.");
        System.out.println("Port name: " + portName);
        System.out.println("Baud rate: " + baudRate);
        System.out.println("Stop bits: " + stopBits);
        System.out.println("Data bits: " + dataBits);
        System.out.println("Parity: " + parity);

        serialPort = new SerialPort(portName);
        MessageEventListener listener = new MessageEventListener();

        try {
            serialPort.openPort();
            serialPort.setParams(baudRate, dataBits, stopBits, parity);
        } catch (SerialPortException e) {
            System.out.println(e.getMessage());
        }

        try {
            serialPort.addEventListener(listener);
        } catch (SerialPortException e) {
            System.out.println(e.getMessage());
        }


        scanner = new Scanner(System.in);
        while (!exit) {
            switch (command = scanner.nextLine()) {
                case "sd":
                    System.out.println("Execute SHUTDOWN command.");
                    try {
                        serialPort.removeEventListener();
                    } catch (SerialPortException e) {
                        System.out.println(e.getMessage());
                    }
                    exit = true;
                    break;
                case "pl":
                    System.out.println("Execute PORTLIST command.");
                    for (String s : SerialPortList.getPortNames()) {
                        System.out.println(s);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
