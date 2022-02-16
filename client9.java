import java.io.*;
import java.net.*;

class client9 {
    public static void main(String[] args) {
        try {
            String str1, str2;
            DatagramSocket ds;
            DatagramPacket incoming, outgoing;
            byte[] data1 = new byte[1024];
            byte[] data2 = new byte[1024];
            BufferedReader br;

            int serverport = 1500;
            int clientport = 1700;
            ds = new DatagramSocket(clientport);
            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // create an incoming packet to receive data
                incoming = new DatagramPacket(data1, data1.length);
                ds.receive(incoming);

                // Parse and display data from incoming packet
                str1 = new String(incoming.getData());
                System.out.println("\nLength is " + incoming.getLength());
                System.out.println("The Received Data:\n" + str1);
                if (str1.equals("bye"))
                    break;

                // get input from console
                System.out.println("Enter the data to send");
                str2 = br.readLine();
                data2 = str2.getBytes();

                // create an outgoing packet to send data
                outgoing = new DatagramPacket(data2, data2.length, InetAddress.getLocalHost(), serverport);
                ds.send(outgoing);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}