package udp.noconcurrente;

/* Comunicaciones en red. UDP no concurrente.
Implementar el cliente y el servidor del protocolo “¿Qué hora es?” utilizando UDP en modo no-
concurrente. El protocolo funciona de la siguiente manera: el cliente envía un datagrama vacío
contra el servidor y este le responde con la fecha y hora actual según su reloj en formato “[dia del
mes]/[mes]/[año] [hora]:[minutos]:[segundos]\n” todos en formato numérico (por ejemplo,
21/09/2013 17:07:34). Para la realización de este ejercicio utilizar la clase
java.text.SimpleDateFormat.
El ejecutable del servidor debe admitir como parámetros el puerto del servicio, y si no se pasa
ninguno este debe ser el puerto 12345. El ejecutable del cliente debe admitir como parámetros
obligatorios la dirección y el puerto del servidor.
*/

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;


public class DaytimeUDPClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Has de donar el nom del servidor i el port");
            System.exit(-1);
        }
        String server = args[0];
        int port = Integer.parseInt(args[1]);

        byte[] data = new byte[0];
        DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName(server), port);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(dp);

        byte[] buffer = new byte[1496];
        dp = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(dp);
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(dp.getData()));
        String time = dis.readUTF();
        System.out.println(time);
    }
}
