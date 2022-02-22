import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class server7 extends JFrame implements ActionListener {
    JTextArea textarea;
    JScrollPane scrollpane;
    JLabel label;
    JTextField textfield;
    JPanel p1, p2;
    JButton send, close;

    ServerSocket server;
    Socket client;
    DataInputStream input;
    DataOutputStream output;

    public server7() {
        super("server");

        textarea = new JTextArea(20, 5);
        scrollpane = new JScrollPane(textarea);
        label = new JLabel("Enter Text");
        textfield = new JTextField(20);
        send = new JButton("send");
        close = new JButton("close");
        p1 = new JPanel();
        p2 = new JPanel();

        p1.add(label);
        p1.add(textfield);

        p2.add(send);
        p2.add(close);

        send.addActionListener(this);
        close.addActionListener(this);

        add(scrollpane, BorderLayout.CENTER);
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);

        setSize(400, 350);
        setVisible(true);
        try {
            int serverport = 5000;
            server = new ServerSocket(serverport, 100);
            client = server.accept();
            textarea.setText("Connected to client!\n");
            output = new DataOutputStream(client.getOutputStream());
            input = new DataInputStream(client.getInputStream());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == send) {
                String s = textfield.getText();
                textarea.append("\nServer: " + s);
                output.writeUTF(s);
            }
            if (ae.getSource() == close) {
                textarea.append("Transmission complete.closing socket \n");
                client.close();
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void runServer() {
        while (true) {
            try {
                String s = input.readUTF();
                textarea.append("\nClient: " + s);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                return;
            }
        }
    }

    public static void main(String args[]) {
        new server7().runServer();
    }
}
