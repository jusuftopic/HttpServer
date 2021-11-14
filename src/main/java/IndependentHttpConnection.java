import java.io.*;
import java.net.Socket;

public class IndependentHttpConnection extends Thread{

    private Socket socket;

    public IndependentHttpConnection(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
             inputStream = socket.getInputStream();
             outputStream = socket.getOutputStream();

            //TODO make it more flexible to parse complex paths
            String requestedPath = HttpRequestParser.getPath(inputStream);

            System.out.println(requestedPath);
            if (requestedPath.equals("/index.html")){
                String html = readHTMLFile(ProjectConstants.DOCUMENT_ROOT+ "index.html");
                if (html != null){
                    final String CRLF = "\n\r";
                    String response = "HTTP/0.9 200 OK" + CRLF +
                            CRLF + html + CRLF + CRLF;

                    outputStream.write(response.getBytes());
                }
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
                if (socket != null) socket.close();
            }
            catch (IOException ioException){
                ioException.printStackTrace();
            }
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
}
