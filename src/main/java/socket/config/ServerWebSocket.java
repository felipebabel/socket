package socket.config;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class ServerWebSocket extends WebSocketServer {

    private ConcurrentHashMap<WebSocket, String> clients = new ConcurrentHashMap<>();

    public ServerWebSocket(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        clients.put(webSocket, "");
        System.out.println("New connection from: " + webSocket.getRemoteSocketAddress().getAddress().getHostName() + " on port: " + webSocket.getRemoteSocketAddress().getPort());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String reason, boolean b) {
        clients.remove(webSocket);
        System.out.println("Connection closed from: " + webSocket.getRemoteSocketAddress().getAddress().getHostName() + " on port: " + webSocket.getRemoteSocketAddress().getPort() + " Reason: " + reason);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("Message received: " + message + " from: " + webSocket.getRemoteSocketAddress().getAddress().getHostName() + " on port: " + webSocket.getRemoteSocketAddress().getPort());
        webSocket.send("Message received: " + message + " from: " + webSocket.getRemoteSocketAddress().getAddress().getHostName() + " on port: " + webSocket.getRemoteSocketAddress().getPort());
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.err.println("WebSocket error: " + e.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started on port: " + getPort());

    }
}
