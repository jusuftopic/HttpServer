package utils;

public class HttpResponse {

    public static final String CRLF = "\n\r";

    public static String getResponseOK(String content){
        return  "HTTP/1.1 200 OK" + CRLF +
                CRLF + content + CRLF + CRLF;
    }

    public static String getResponseNotFound() {
        return "HTTP/1.1 404 NOT FOUND" + CRLF +
           CRLF +"<HTML>" +
                "<HEAD><TITLE>Not found</TITLE></HEAD>" +
                "<BODY>Opps something went wrong... " +
                "Please try to reach some other endpoint.</BODY></HTML>" + CRLF + CRLF;
    }
}
