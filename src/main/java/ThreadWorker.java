public class ThreadWorker implements Runnable {
    private ICOMRequestProcessor processor;

    public ThreadWorker(ICOMRequestProcessor _processor) {
        processor = _processor;
    }

    @Override
    public void run() {
        System.out.println("ThreadWorker.run() " + processor.getInfo());
        processor.processAsync();
    }
}
