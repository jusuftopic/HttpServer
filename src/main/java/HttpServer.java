
public class HttpServer {

    public static void main(String[] args) {
        MultiThreadingHandler multiThreadingHandler = new MultiThreadingHandler(ProjectConstants.PORT,ProjectConstants.DOCUMENT_ROOT);
        multiThreadingHandler.start();
    }

}