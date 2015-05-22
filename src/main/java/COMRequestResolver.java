public class COMRequestResolver {
    public ICOMRequestProcessor getCurrentHandler(COMRequest request) {
        ICOMRequestProcessor processor = new TestMessageHandler();
        processor.setCOMRequest(request);
        return processor;
    }
}
