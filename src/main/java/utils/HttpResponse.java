package utils;

import static utils.ProjectConstants.CT_TEXT_HTML;
import static utils.ProjectConstants.CONNECTION_KEEP_ALIVE;
import static utils.ProjectConstants.CONNECTION_CLOSE;

public class HttpResponse {

    public static final String CRLF = "\n\r";

    public static String getResponseOK(String content){
        return  "HTTP/1.1 200 OK" + CRLF +
                "Content-Type: "+ CT_TEXT_HTML + CRLF +
                "Content-Length: " + content.length() + CRLF +
                "Connection: " + CONNECTION_KEEP_ALIVE + CRLF +
                CRLF + content + CRLF + CRLF;
    }

    public static String getResponseNotFound() {
        return "HTTP/1.1 404 NOT FOUND" + CRLF +
                "Content-Type: "+  CT_TEXT_HTML + CRLF +
                "Connection: " + CONNECTION_CLOSE + CRLF +
           CRLF +"<HTML>" +
                "<HEAD><TITLE>Not found</TITLE></HEAD>" +
                "<BODY>Opps something went wrong... " +
                "Please try to reach some other endpoint.</BODY></HTML>" + CRLF + CRLF;
    }

    public static String getResponseInternalError(){
        return "HTTP/1.1 500 INTERNAL SERVER ERROR" + CRLF +
                CRLF +"<HTML>" +
                "<HEAD><TITLE>Not found</TITLE></HEAD>" +
                "<BODY>Opps something went wrong... " +
                "Please try to reach some other endpoint.</BODY></HTML>" + CRLF + CRLF;
    }
}
