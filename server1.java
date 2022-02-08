// import java.io.DataInputStream;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.PrintStream;

import java.io.*;
import java.net.*;

public class server1 {
    public static void main(String args[]) {
        try {
            int serverport = 8020;
            ServerSocket server = new ServerSocket(serverport);
            System.out.println("Server waiting for connection...");
            Socket client = server.accept();
            System.out.println("SERVER SOCKET IS CREATED");
            //receives data from client (like a container)
            DataInputStream input = new DataInputStream(client.getInputStream());

            String option = input.readLine();
            if (option.equals("upload")) {
                System.out.println("Upload text");
                String inputfile = input.readLine();

                //points to location and creates an empty file with name "inputfile"
                File serverfile = new File("./server-files/"+inputfile);

                //writer to write data into the file with name "serverfile"
                FileOutputStream fout = new FileOutputStream(serverfile);
                
                //process to writing data into the file,character by character
                int ch;
                while ((ch = input.read()) != -1) {
                    fout.write((char) ch);
                }
                fout.close();
            }
            if (option.equals("download")) {
                System.out.println("dowload text");
                String inputfile = input.readLine();

                File clientfile = new File("./client-files/"+inputfile);
                //read
                FileInputStream fin = new FileInputStream(clientfile);

                //send file to client
                PrintStream out = new PrintStream(client.getOutputStream());
                int n = fin.read();
                while (n != -1) {
                    out.print((char) n);
                    n = fin.read();
                }
                fin.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}