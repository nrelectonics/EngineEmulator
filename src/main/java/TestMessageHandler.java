import jssc.SerialPort;
import jssc.SerialPortException;

public class TestMessageHandler implements ICOMRequestProcessor {

    final SerialPort serialPort;
    COMRequest request;

    public TestMessageHandler(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public void setCOMRequest(COMRequest _request) {
        request = _request;
    }

    @Override
    public String getInfo() {
        return "ICOMRequestProcessor.getInfo()";
    }

    @Override
    public void processAsync() {
        System.out.println("Request msg: " + request.getMessage());
        String response = "RESPONSE";
        synchronized (serialPort) {
            System.out.println("Process sync writing to COM port: " + response);
            try {
                serialPort.writeString(response);
            } catch (SerialPortException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
