import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class client7 extends JFrame implements ActionListener {
    JTextArea textarea;
    JScrollPane scrollpane;
    JLabel label;
    JTextField textfield;
    JButton b1, b2;
    JPanel p1, p2;

    Socket client;
    DataInputStream input;
    DataOutputStream output;

    public client7() {
        super("client");

        textarea = new JTextArea(20, 10);
        scrollpane = new JScrollPane(textarea);
        label = new JLabel("Enter Text");
        textfield = new JTextField(20);
        b1 = new JButton("send");
        b2 = new JButton("close");
        p1 = new JPanel();
        p2 = new JPanel();

        p1.add(label);
        p1.add(textfield);

        p2.add(b1);
        p2.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        add(scrollpane, BorderLayout.CENTER);
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);

        setSize(400, 350);
        setVisible(true);
        try {
            int serverport = 5000;
            client = new Socket("localhost", serverport);
            textarea.append("Socket Created!\n");
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String s = textfield.getText();
                output.writeUTF(s);
                textarea.append("\nClient: " + s);
            }
            if (ae.getSource() == b2) {
                client.close();
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void runClient() {
        while (true) {
            try {
                String s = input.readUTF();
                textarea.append("\nServer: " + s);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                return;
            }
        }
    }

    public static void main(String args[]) {
        new client7().runClient();
    }
}