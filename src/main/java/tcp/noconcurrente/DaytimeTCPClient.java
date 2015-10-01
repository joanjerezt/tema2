package tcp.noconcurrente;

/* Implementar el cliente y el servidor del protocolo “¿Qué hora es?” utilizando TCP en modo no-
concurrente. El protocolo funciona de la siguiente manera: el cliente abre una conexión contra el
servidor y este le responde con la fecha y hora actual según su reloj en formato “[dia del mes]/
[mes]/[año] [hora]:[minutos]:[segundos]\n” todos en formato numérico (por ejemplo, 21/09/2013
17:07:34). Para la realización de este ejercicio utilizar la clase java.text.SimpleDateFormat.
El ejecutable del servidor debe admitir como parámetros el puerto del servicio, y si no se pasa
ninguno este debe ser el puerto 12345. El ejecutable del cliente debe admitir como parámetros
obligatorios la dirección y el puerto del servidor.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DaytimeTCPClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Has d'introduir el nom del servidor i el port");
            System.exit(-1);
        }
        String server = args[0];
        int port = Integer.parseInt(args[1]);

        Socket socket = new Socket(server, port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String time = reader.readLine();
        System.out.println(time);
    }
}
