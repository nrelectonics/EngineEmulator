import jssc.SerialPort;

public class COMRequestResolver {
    public ICOMRequestProcessor getCurrentHandler(COMRequest request, SerialPort serialPort) {
        ICOMRequestProcessor processor = new TestMessageHandler(serialPort);
        processor.setCOMRequest(request);
        return processor;
    }
}
