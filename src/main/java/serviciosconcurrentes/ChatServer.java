package serviciosconcurrentes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer implements Runnable {
    private int port;
    private Thread thread;

    public final static int DEFAULT_PORT = 3333;

    public ChatServer(int port) {
        this.port = port;
    }

    public void startServer() {
        if (thread == null)
            (thread = new Thread(this, "Fil principal del servidor")).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Servidor del xat actiu, escoltant a " + port);
            while (true) {
                Socket socket = ss.accept();
                (new Thread(new ChatServerThread(socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = (args.length == 1) ? Integer.parseInt(args[0]) : DEFAULT_PORT;
        ChatServer server = new ChatServer(port);
        server.startServer();
    }
}
