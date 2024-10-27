package conn;

import com.sun.net.httpserver.HttpServer;
import main.ArgsChecker;

import java.io.IOException;
import java.net.InetSocketAddress;

public class CalcHttpServer {

    public CalcHttpServer() {
    }

    public void setServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(ArgsChecker.getPort()), 0);
        server.createContext("/v1/calc", new CalcHttpHandler());
        server.setExecutor(null);
        server.start();
    }
}

