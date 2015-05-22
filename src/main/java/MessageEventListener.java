import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageEventListener implements SerialPortEventListener {
    ExecutorService executorService;
    COMRequestResolver comRequestResolver;

    public MessageEventListener() {
        executorService = Executors.newFixedThreadPool(10);
        comRequestResolver = new COMRequestResolver();
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        COMRequest request = new COMRequest();
        try {
            request.setMessage(EngineEmulator.serialPort.readString());
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        ICOMRequestProcessor processor = comRequestResolver.getCurrentHandler(request);
        ThreadWorker worker = new ThreadWorker(processor);
        executorService.execute(worker);
        System.out.println("Serial port event type: " + serialPortEvent.getEventType());
    }
}
