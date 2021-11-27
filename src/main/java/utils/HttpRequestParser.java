package utils;

import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class HttpRequestParser {

    public static String parseRequest(InputStream inputStream){
        StringBuilder requestHeader = new StringBuilder();
        Reader reader = new InputStreamReader(inputStream);

        try {
            int c;
            while ((c = reader.read()) != -1){
                requestHeader.append((char) c);

                if (requestHeader.indexOf("\r\n\r\n") != -1) {break;}
            }

            while (reader.ready()){
                requestHeader.append((char) reader.read());
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        
        return requestHeader.toString();
    }

}
