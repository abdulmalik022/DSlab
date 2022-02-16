import java.io.*;
import java.net.*;

class server9 {
    public static void main(String[] args) {
        try {
            String str1, str2;
            DatagramSocket ds;
            DatagramPacket dp1, dp2;
            InetAddress ia;
            byte[] data1 = new byte[1024];
            byte[] data2 = new byte[1024];
            BufferedReader br1;
            ds = new DatagramSocket(1500);
            ia = InetAddress.getLocalHost();
            br1 = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Enter the data to send");
                str1 = br1.readLine();
                data1 = str1.getBytes();
                dp1 = new DatagramPacket(data1, data1.length, ia, 1700);
                ds.send(dp1);
                if (str1.equals("bye"))
                    break;
                dp2 = new DatagramPacket(data2, data2.length);
                ds.receive(dp2);
                str2 = new String(dp2.getData(), 0, dp2.getLength());
                System.out.println("The Received Data " + str2);
                if (str2.equals("bye"))
                    break;
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