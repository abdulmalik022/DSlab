import java.net.*;
import java.io.*;

public class client4 {
    public static void main(String args[]) {
        Socket client = null;
        try {
            int serverport = 7896;
            client = new Socket(args[0], serverport);

            //InputStream
            DataInputStream in = new DataInputStream(client.getInputStream());
            //OutputStream
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

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
            if (client != null)
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println("close failed");
                }
        }
    }
}