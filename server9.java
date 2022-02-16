import java.io.*;
import java.net.*;

class server9 {
    public static void main(String[] args) {
        try {
            String str1, str2;
            DatagramSocket ds;
            DatagramPacket request, reply;
            byte[] data1 = new byte[1024];
            byte[] data2 = new byte[1024];
            BufferedReader br1;

            int serverport = 1500;
            int clientport = 1700;
            ds = new DatagramSocket(serverport);
            br1 = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Enter the data to send");
                str1 = br1.readLine();
                data1 = str1.getBytes();
                request = new DatagramPacket(data1, data1.length, InetAddress.getLocalHost(), clientport);
                ds.send(request);
                if (str1.equals("bye"))
                    break;

                reply = new DatagramPacket(data2, data2.length);
                ds.receive(reply);
                // str2 = new String(reply.getData(), 0, reply.getLength());
                str2 = new String(reply.getData());
                System.out.println("The Received Data:\n" + str2);
                if (str2.equals("bye"))
                    break;
            }
        } catch(Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }
    }
}