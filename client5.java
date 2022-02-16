import java.net.*;

public class client5 {
    public static void main(String[] args) {
        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
            byte[] buffer = new byte[1000];

            InetAddress host = InetAddress.getByName(args[0]);
            int serverport = 6789;
            DatagramPacket request = new DatagramPacket(args[1].getBytes(),args[1].length(),host,serverport);
            client.send(request);

            DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
            client.receive(reply);

            String msg = new String(reply.getData());
            System.out.println("Data received from server: "+msg);
        } catch(Exception e) {
            System.out.println("Exception: "+e.getMessage());
        } finally {
            if(client!=null) {
                client.close();
            }
        }
    }
}