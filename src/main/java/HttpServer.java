import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(ProjectConstants.PORT);
            Socket socket = serverSocket.accept();

            System.out.println("Server started at port: " + serverSocket.getLocalPort());

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String requestedPath = HttpRequestParser.getPath(inputStream);

            if (requestedPath.substring(1).equals("index.html")){
                String html = readHTMLFile(ProjectConstants.DOCUMENT_ROOT+ "index.html");
                if (html != null){
                    final String CRLF = "\n\r";
                    String response = "HTTP/0.9 200 OK" + CRLF +
                            CRLF + html + CRLF + CRLF;

                    outputStream.write(response.getBytes());
                }
            }

            closeConnection(inputStream, outputStream, socket, serverSocket);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    private static String readHTMLFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String s;

            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }

            bufferedReader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static void closeConnection(InputStream inputStream, OutputStream outputStream, Socket socket, ServerSocket serverSocket) {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    }