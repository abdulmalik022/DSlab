import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class pracserver3 extends JFrame implements ActionListener {
    JButton sendbtn = new JButton("SEND");
    JLabel label = new JLabel("Enter your message:");
    JTextField textfield = new JTextField(20);
    JTextArea textarea = new JTextArea(20, 20);
    JScrollPane scrollpane = new JScrollPane(textarea);
    JPanel panel = new JPanel();

    ServerSocket server;
    Socket client;
    DataInputStream din;
    PrintWriter pw;

    public pracserver3() {
        super("Server Window");
        panel.add(label);
        panel.add(textfield);
        panel.add(sendbtn);
        add(panel, BorderLayout.SOUTH);
        add(scrollpane);
        textarea.setEditable(false);
        sendbtn.addActionListener(this);
        textfield.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        try {
            int serverport = 6789;
            server = new ServerSocket(serverport);
            textarea.setText("Server is waiting for Client\n");
            client = server.accept();
            textarea.append("Client is now Connected\n");
            din = new DataInputStream(client.getInputStream());
            pw = new PrintWriter(client.getOutputStream());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void receive() {
        try {
            String msg;
            while((msg=din.readLine()) != null) {
                String temp = "\nClient: "+msg;
                textarea.append(temp);
            }
        } catch(Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try{
            String msg = "\nServer: "+textfield.getText();
            textarea.append(msg);
            pw.println(textfield.getText());
            pw.flush();
            textfield.setText("");
        } catch(Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        new pracserver3().receive();
    }
}
