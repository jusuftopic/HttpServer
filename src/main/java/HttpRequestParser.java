import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class HttpRequestParser {

    public HttpRequestParser(){
    }

    public static String parseRequest(InputStream inputStream){
        String parsedRequest = "";
        Reader reader = new InputStreamReader(inputStream);

        try {
            int c;
            while ((c = reader.read()) != -1){
                parsedRequest += (char) c;

                if (parsedRequest.contains("\r\n\r\n")) {break;};
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        System.out.println("--------------------------");
        System.out.println(parsedRequest);
        System.out.println("-----------------------------");

        return parsedRequest.split("\n")[0];
    }

}
