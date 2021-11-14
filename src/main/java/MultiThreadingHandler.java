import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadingHandler extends Thread{

    private int port;
    private String webRoot;
    private ServerSocket serverSocket;

    public MultiThreadingHandler(int port, String webRoot){
        this.port = port;
        this.webRoot = webRoot;
        try {
            this.serverSocket = new ServerSocket(port);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while (this.serverSocket.isBound() && !this.serverSocket.isClosed()){
            Socket socket = serverSocket.accept();

            System.out.println("Server started at port: " + serverSocket.getLocalPort());

            IndependentHttpConnection independentHttpConnection = new IndependentHttpConnection(socket);
            independentHttpConnection.start();
        }}
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
