import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private int port;
    private String rootDoc;
    private ServerSocket serverSocket;

    public HttpServer(int port, String rootDoc){
        this.port =port;
        this.rootDoc = rootDoc;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void startListen(){
        try {
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Server is running on port: "+ serverSocket.getLocalPort());

                HttpRequest httpRequest = new HttpRequest(socket, rootDoc);

                new Thread(httpRequest).start();
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        finally {
            if (this.serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
