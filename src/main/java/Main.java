

public class Main {

    public static void main(String[] args) {
       HttpServer httpServer =
               new HttpServer(ProjectConstants.PORT, ProjectConstants.DOCUMENT_ROOT);

       httpServer.startListen();
    }

}