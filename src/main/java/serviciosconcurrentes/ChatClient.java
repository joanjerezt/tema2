package serviciosconcurrentes;

/* Diseñar e implementar un servicio de chat sobre protocolo de transporte TCP. Los clientes se unen
al chat enviando el mensaje
JOIN [name]\n
siendo [name] el nombre de usuario que escoge el cliente. Los clientes abandonan el chat enviando
el mensaje:
LEAVE\n
Para enviar un mensaje el cliente enviará:
MESSAGE [message]\n
Una vez unido un cliente, todos los mensajes que envíe serán reenviados por el servidor a todos los
clientes unidos al chat (el cliente que envía el mensaje inclusive) enviando:
[name]> [message]\n
siendo [name] el nombre de usuario del cliente origen del mensaje. Cuando un cliente abandone el
chat, todos los demás recibirán el mensaje:
[name]> me piro.
Y cuando se una:
[name]> estoy dentro.
La entrada de mensajes se realizará a través de la entrada estándar. Si el mensaje que entra el
usuario tiene longitud cero, esto es, pulsa únicamente la tecla enter, entonces el cliente envía el
mensaje para abandonar el chat. Obviar cualquier situación que se aparte del protocolo descrito
como, por ejemplo, que dos usuarios se unan con el mismo [name].
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient implements Runnable {
    private class ReaderThread implements Runnable {
        BufferedReader reader = null;

        public ReaderThread(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String msg = null;
            try {
                while ((msg = reader.readLine()) != null)
                    System.out.println(msg);
            } catch (IOException e) {
                if (!socket.isClosed())
                    e.printStackTrace();
            }
        }
    }

    private String server;
    private int port;
    private Socket socket = null;
    private BufferedReader reader = null;
    private PrintWriter writer = null;

    public ChatClient(String server) {
        this(server, ChatServer.DEFAULT_PORT);
    }

    public ChatClient(String server, int port) {
        this.server = server;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introdueix el nom d'usuari");
            String username = reader.readLine();
            join(username);

            System.out.println("Comença el xat");
            String msg = null;
            while ((msg = reader.readLine()).length() != 0) {
                send(msg);
            }
            leave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void join(String username) throws IOException {
        socket = new Socket(server, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        (new Thread((new ReaderThread(reader)))).start();
        writer = new PrintWriter(socket.getOutputStream());

        writer.println("JOIN " + username);
        writer.flush();
    }

    private void leave() throws IOException {
        writer.println("LEAVE");
        writer.flush();

        socket.close();
    }

    private void send(String msg) {
        writer.println("MESSAGE " + msg);
        writer.flush();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Has d'entrar el nom del servidor i el port");
            System.exit(-1);
        }
        String server = args[0];
        int port = Integer.parseInt(args[1]);
        ChatClient client = new ChatClient(server, port);
        (new Thread(client)).start();
    }
}
