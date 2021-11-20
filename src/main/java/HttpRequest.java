import utils.HttpResponse;

import java.io.*;
import java.net.Socket;

public class HttpRequest implements Runnable {
    private Socket socket;
    private String documentRoot;

    public HttpRequest(Socket socket, String documentRoot) {
        this.socket = socket;
        this.documentRoot = documentRoot;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();


            String parsedHttpRequest = HttpRequestParser.parseRequest(inputStream);
            if (parsedHttpRequest != null && parsedHttpRequest.length() > 0){
            String httpMethod = parsedHttpRequest.trim().split(" ")[0];
            String httpPath = parsedHttpRequest.trim().split(" ")[1];

                System.out.println(parsedHttpRequest);

           switch (httpMethod){
               case ProjectConstants.GET: handleGetRequest(outputStream, httpPath);
               break;
               case ProjectConstants.POST:
                   System.out.println("a");
                   break;
           }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null){
                    socket.close();
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void handleGetRequest(OutputStream outputStream, String path){
        String html = "";
        String response = "";
        if (path.equals(ProjectConstants.FORM)){
            html = readHTMLFile(ProjectConstants.DOCUMENT_ROOT+ "/form.html");
        }
        else if (path.equals(ProjectConstants.INDEX)){
            html = readHTMLFile(ProjectConstants.DOCUMENT_ROOT + "/index.html");
        }
        else{
            response = HttpResponse.getResponseNotFound();
            try {
                outputStream.write(response.getBytes());
                return;
            }
            catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
        try {
            if (html != null && html.length() > 0){
                response = HttpResponse.getResponseOK(html);
            }
            else{
                response = HttpResponse.getResponseNotFound();
            }

            outputStream.write(response.getBytes());

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
}
