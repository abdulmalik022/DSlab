import java.net.*;

public class server5 {
    public static void main(String args[]) {
        DatagramSocket server = null;
        try {
            int serverport = 6789;
            server = new DatagramSocket(serverport);
            byte[] buffer = new byte[1000];

            System.out.println("Server is listening:");
            while (true) {

                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                server.receive(request);

                String msg = new String(request.getData());
                System.out.println("Data received from client: " + msg);

                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),
                        request.getPort());
                server.send(reply);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            if (server != null) {
                server.close();
            }
        }
    }
}