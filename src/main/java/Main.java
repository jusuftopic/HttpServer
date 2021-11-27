import static utils.ProjectConstants.PORT;
import static utils.ProjectConstants.DOCUMENT_ROOT;

import utils.ProjectConstants;

public class Main {

    public static void main(String[] args) {
       HttpServer httpServer =
               new HttpServer(PORT, DOCUMENT_ROOT);

       httpServer.startListen();
    }

}