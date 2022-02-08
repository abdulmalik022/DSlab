import java.util.*;
import java.net.*;
import java.io.*;

public class server2 {
    ServerSocket server;
    Socket client;
    DataInputStream input;
    DataOutputStream output;
    FileInputStream fin = null;
    FileOutputStream fout = null;
    Properties props;

    server2() {
        try {
            System.out.println("Server is running...");
            
            //instantiate empty property 
            props = new Properties();
            //read contents from NameList.dat and load it into 'props'
            fin = new FileInputStream("Namelist.dat");
            if (fin != null) {
                props.load(fin);
                fin.close();
            }

            //Initialize connection with client
            int serverport = 1500;
            server = new ServerSocket(serverport);
            client = server.accept();
            System.out.println("Client connected");
            output = new DataOutputStream(client.getOutputStream());
            input = new DataInputStream(client.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void runserver() {
        
        while (true) {
            try {
                String option = input.readUTF();
                if (option.equals("lookup")) {
                    String s2 = input.readUTF();
                    System.out.println(s2);
                    String s1 = lookup(s2);
                    if (s1 == null)
                        output.writeUTF("Host name not found");
                    else
                        output.writeUTF("IP address of " + s2 + "is" + s1);
                }
                if (option.equals("add")) {
                    String s1 = input.readUTF();
                    String s2 = input.readUTF();
                    boolean b = addHost(s1, s2);
                    if (b == true)
                        output.writeUTF("Host name registered");
                    else
                        output.writeUTF("Ip address already exists");
                }
                if (option.equals("remove")) {
                    String s1 = input.readUTF();
                    boolean b = removeHost(s1);
                    if (b == true)
                        output.writeUTF("Hostname removed");
                    else
                        output.writeUTF("Invalid hostname");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    boolean addHost(String name, String ip) {
        if (props.get(name) != null)
            return false;
        else
            props.put(name, ip);
        try {
            fout = new FileOutputStream("NameList.dat");
            props.store(fout, "Namespace");
            fout.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    boolean removeHost(String name) {
        if (props.get(name) != null)
            props.remove(name);
        try {
            fout = new FileOutputStream("NameList.dat");
            props.store(fout, "NameSpace");
            fout.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    String lookup(String host) {
        return (String) props.get(host);
    }

    public static void main(String args[]) {
        new server2().runserver();
    }
}