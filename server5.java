
import java.net.*;
import java.io.*;

public class server5 {
    public static void main(String args[]) {
        DatagramSocket server = null;
        try {
            int serverport = 6789;
            server = new DatagramSocket(serverport);
            byte[] buffer = new byte[1000];

            while (true) {
                System.out.println("Server Listening:");
                //create a datagram packet to receive data from client to fill its buffer
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                server.receive(request);

                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),
                        request.getPort());

                String re = new String(reply.getData());
                System.out.println("Data received from client:\n" + re);
                server.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket!" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (server != null)
                server.close();
        }
    }
}