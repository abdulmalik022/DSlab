import java.io.*;
import java.net.*;

class client9 {
    public static void main(String[] args) {
        String str1, str2;
        DatagramSocket ds;
        DatagramPacket dp1, dp2;
        InetAddress ia;
        byte[] data1 = new byte[1024];
        byte[] data2 = new byte[1024];
        BufferedReader br;
        try {
            ds = new DatagramSocket(1700);
            ia = InetAddress.getLocalHost();
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                dp1 = new DatagramPacket(data1, data1.length);
                ds.receive(dp1);
                str1 = new String(dp1.getData(), 0, dp1.getLength());
                System.out.println("Length is " + dp1.getLength());
                System.out.println("The Received Data " + str1);
                if (str1.equals("bye"))
                    break;
                System.out.println("Enter the data to send");
                str2 = br.readLine();
                data2 = str2.getBytes();
                dp1 = new DatagramPacket(data2, data2.length, ia, 1500);
                ds.send(dp1);
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