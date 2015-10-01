package udp.noconcurrente;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;

public class DaytimeUDPServer {
    private final static int DEFAULT_PORT = 12345;

    public static void main(String[] args) throws IOException {
        int port = (args.length == 1) ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DatagramSocket datagramSocket = new DatagramSocket(port);
        while (true) {
            byte[] buffer = new byte[0];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeUTF(sdf.format(System.currentTimeMillis()));
            byte[] data = baos.toByteArray();

            datagramPacket = new DatagramPacket(data, data.length, datagramPacket.getAddress(), datagramPacket.getPort());
            datagramSocket.send(datagramPacket);
        }
    }
}
