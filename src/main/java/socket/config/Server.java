package socket.config;

public class Server {

    public static void main(String[] args) {
        int port = 8080;
        ServerWebSocket server = new ServerWebSocket(port);
        server.start();
    }
}
