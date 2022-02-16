import java.io.*;
import java.net.*;

class client9 {
    public static void main(String[] args) {
        try {
            String str1, str2;
            DatagramSocket ds;
            DatagramPacket request, reply;
            byte[] data1 = new byte[1024];
            byte[] data2 = new byte[1024];
            BufferedReader br;

            int serverport = 1500;
            int clientport = 1700;
            ds = new DatagramSocket(clientport);
            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                request = new DatagramPacket(data1, data1.length);
                ds.receive(request);
                // str1 = new String(request.getData(), 0, request.getLength());
                str1 = new String(request.getData());
                System.out.println("\nLength is " + request.getLength());
                System.out.println("The Received Data:\n" + str1);
                if (str1.equals("bye"))
                    break;
                System.out.println("Enter the data to send");
                str2 = br.readLine();
                data2 = str2.getBytes();
                reply = new DatagramPacket(data2, data2.length, InetAddress.getLocalHost(), serverport);
                ds.send(reply);
            }
        } catch (BindException e) {
            System.out.println(e);
        } catch (SocketException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}