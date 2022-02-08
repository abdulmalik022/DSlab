import java.io.*;
import java.net.*;

public class server4 {
    public static void main(String args[]) {
        try {
            //storing port number
            int serverport = 7896;
            //creating a SERVER socket with specified port number
            ServerSocket listensocket = new ServerSocket(serverport);
            System.out.println("Server Active...");
            while (true) {
                //returns client Socket object when connection is successfully established with client
                Socket clientsocket = listensocket.accept(); //blocked until connected to client

                //User-defined class to perform read write operations on the client
                Connection c = new Connection(clientsocket);
            }
        } catch (IOException e) {
            System.out.println("LISTEN:" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientsocket;

    public Connection(Socket aclientsocket) {
        try {
            clientsocket = aclientsocket;
            in = new DataInputStream(clientsocket.getInputStream());
            out = new DataOutputStream(clientsocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            //read message from client
            String data = in.readUTF();
            //send message to client
            out.writeUTF(data);
            System.out.println("Received data from client:\n"+data);
        } catch(Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        // catch (EOFException e) {
        //     System.out.println("EOF:" + e.getMessage());
        // } catch (IOException e) {
        //     System.out.println("IO:" + e.getMessage());
        // } 
        finally {
            try {
                clientsocket.close();
            } catch (IOException e) {
                System.out.println("close failed");
            }
        }
    }
}