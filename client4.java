import java.net.*;
import java.io.*;

public class client4 {
    public static void main(String args[]) {
        Socket s = null;
        try {
            int serverport = 7896;
            s = new Socket(args[0], serverport);

            //InputStream
            DataInputStream in = new DataInputStream(s.getInputStream());
            //OutputStream
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            //send message to server
            out.writeUTF(args[1]);
            //receive message from server
            String data = in.readUTF();
            System.out.println("Received data from server: \n" + data);
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close failed");
                }
        }
    }
}