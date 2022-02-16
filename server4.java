import java.io.*;
import java.net.*;

public class server4 {
    public static void main(String args[]) {
        try {
            // storing port number
            int serverport = 7896;
            // creating a SERVER socket with specified port number
            ServerSocket server = new ServerSocket(serverport);
            System.out.println("Server Active...");
            while (true) {
                // returns client Socket object when connection is successfully established with
                // client
                Socket client = server.accept(); // blocked until connected to client

                // User-defined class to perform read write operations on the client
                Connection c = new Connection(client);
            }
        } catch (IOException e) {
            System.out.println("LISTEN:" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket client = null;

    public Connection(Socket aclient) {
        try {
            client = aclient;
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            // read message from client
            String msg = in.readUTF();
            System.out.println("Data received from client:\n" + msg);
            // send message to client
            out.writeUTF(msg);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        // catch (EOFException e) {
        // System.out.println("EOF:" + e.getMessage());
        // } catch (IOException e) {
        // System.out.println("IO:" + e.getMessage());
        // }
        finally {
            if (client != null)
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println("close failed");
                }
        }
    }
}