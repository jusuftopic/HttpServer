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

                if (parsedRequest.contains("\r\n\r\n")) break;
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        return parsedRequest;
    }

    public static String getPath(InputStream inputStream){
        String parsedRequest[] = parseRequest(inputStream).split("\n");

        return parsedRequest[0].trim().split(" ")[1];
    }
}
