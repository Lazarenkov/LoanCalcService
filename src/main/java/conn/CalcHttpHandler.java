package conn;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import exeptions.CalcErrorException;
import exeptions.DBConnectionException;
import exeptions.DBRetrievingException;
import exeptions.NoDBModeException;
import io.http.HttpService;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CalcHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        switch (t.getRequestMethod()) {
            case "GET":
                handleGETRequest(t);
                break;
            case "POST":
                handlePOSTRequest(t);
                break;
            case "DELETE":
                handleDELETERequest(t);
                break;
            case "OPTIONS":
                handleOPTIONSRequest(t);
                break;
            case "HEAD":
                handleHEADRequest(t);
                break;
            default:
                handleNotAllowedRequest(t);
                break;
        }
    }

    private void handleGETRequest(HttpExchange t) throws IOException {
        try {
            String query = t.
                    getRequestURI()
                    .toString()
                    .split("\\?")[1].split("=")[1];
            byte[] response = HttpService.getOldTarget(query);

            OutputStream outputStream = t.getResponseBody();
            if (response == null) {
                t.sendResponseHeaders(404, -1);
            } else {
                t.getResponseHeaders().set("Content-Type", "application/json");
                t.sendResponseHeaders(200, response.length);
                outputStream.write(response);
            }
            outputStream.flush();
            outputStream.close();
            LogManager.getLogger(CalcHttpHandler.class.getName()).info("Выполнена отправка клиенту");
        } catch (CalcErrorException | DBConnectionException e) {
            handle500ErrorResponse(t);
        } catch (DBRetrievingException e) {
            handle404ErrorResponse(t);
        } catch (NoDBModeException e) {
            handle503ErrorResponse(t);
        }
    }

    private void handlePOSTRequest(HttpExchange t) throws IOException {
        try {
            InputStream body = t.getRequestBody();
            byte[] response = HttpService.getNewTarget(body);
            OutputStream outputStream = t.getResponseBody();
            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, response.length);
            outputStream.write(response);
            outputStream.flush();
            outputStream.close();
            LogManager.getLogger(CalcHttpHandler.class.getName()).info("Выполнена отправка клиенту");
        } catch (CalcErrorException | IOException e) {
            handle500ErrorResponse(t);
        }
    }

    private void handleDELETERequest(HttpExchange t) throws IOException {
        try {
            String query = t.
                    getRequestURI()
                    .toString()
                    .split("\\?")[1].split("=")[1];
            HttpService.deleteOldTarget(query);
            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, -1);
            LogManager.getLogger(CalcHttpHandler.class.getName()).info("Удаление выполнено, отправлено клиенту");
        } catch (CalcErrorException | DBConnectionException e) {
            handle500ErrorResponse(t);
        } catch (DBRetrievingException e) {
            handle404ErrorResponse(t);
        } catch (NoDBModeException e) {
            handle503ErrorResponse(t);
        }
    }

    private void handleOPTIONSRequest(HttpExchange t) throws IOException {
        t.getResponseHeaders().set("Allow", "GET, POST, OPTIONS, HEAD");
        t.getResponseHeaders().set("Content-Type", "application/json");
        t.sendResponseHeaders(204, -1);
    }

    private void handleHEADRequest(HttpExchange t) throws IOException {
        t.getResponseHeaders().set("Content-Type", "application/json");
        t.sendResponseHeaders(204, -1);
    }

    private void handleNotAllowedRequest(HttpExchange t) throws IOException {
        t.getResponseHeaders().set("Content-Type", "application/json");
        t.sendResponseHeaders(405, -1);
    }

    private void handle503ErrorResponse(HttpExchange t) throws IOException {
        t.getResponseHeaders().set("Content-Type", "application/json");
        t.sendResponseHeaders(503, -1);
    }

    private void handle404ErrorResponse(HttpExchange t) throws IOException {
        t.getResponseHeaders().set("Content-Type", "application/json");
        t.sendResponseHeaders(404, -1);
    }

    private void handle500ErrorResponse(HttpExchange t) throws IOException {
        t.getResponseHeaders().set("Content-Type", "application/json");
        t.sendResponseHeaders(500, -1);
    }
}
