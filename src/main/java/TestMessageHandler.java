import jssc.SerialPortException;

public class TestMessageHandler implements ICOMRequestProcessor {

    COMRequest request;

    public TestMessageHandler() {
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
        synchronized (EngineEmulator.serialPort) {
            System.out.println("Process sync writing to COM port: " + response);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                EngineEmulator.serialPort.writeString(response);
            } catch (SerialPortException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
