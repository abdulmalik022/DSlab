import java.net.*;
import java.io.*;

public class client5 {
    public static void main(String args[]) {
        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
            byte[] buffer = new byte[1000];

            InetAddress host = InetAddress.getByName(args[0]);
            int serverport = 6789;
            DatagramPacket request = new DatagramPacket(args[1].getBytes(), args[1].length(), host, serverport);
            client.send(request);

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            client.receive(reply);

            String msg = new String(reply.getData());
            System.out.println("Data received from server:\n" + msg);
        } catch (SocketException e) {
            System.out.println("SoCKET!" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (client != null)
                client.close();
        }
    }
}