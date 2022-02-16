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
                //create an incoming packet to receive data
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                server.receive(incoming);

                //Parse and display data from incoming packet
                String msg = new String(incoming.getData());
                System.out.println("Data received from client: " + msg);
                
                //create an outgoing packet to send data
                DatagramPacket outgoing = new DatagramPacket(incoming.getData(), incoming.getLength(), incoming.getAddress(),
                        incoming.getPort());
                server.send(outgoing);
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