import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Created by laptop on 21.05.2015.
 */
public class DevTerminal implements Runnable, SerialPortEventListener {

    private String portName;
    private SerialPort serialPort;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;

    public DevTerminal() {
        this(null, 0, 0, 0, 0);
    }

    public DevTerminal(String _portName, int _baudRate, int _dataBits, int _stopBits, int _parity) {
        portName = _portName;
        baudRate = _baudRate;
        dataBits = _dataBits;
        stopBits = _stopBits;
        parity = _parity;
    }
    @Override
    public void run() {
        System.out.println("DevTerminal.Run()");
        serialPort = new SerialPort(portName);
        try {
            serialPort.openPort();
            serialPort.setParams(baudRate,dataBits,stopBits,parity);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

        try {
            serialPort.addEventListener(this);
        } catch (SerialPortException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        System.out.println("SERIALEVENT_TYPE: " + serialPortEvent.getEventValue());
    }
}

