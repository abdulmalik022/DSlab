import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class client2 extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4;
    JPanel p1, p2, p3;
    JLabel l1, l2;
    JTextField t1, t2, t3;

    Socket client;
    DataOutputStream output;
    DataInputStream input;

    client2() {
        super("Client Window");
        b1 = new JButton("add");
        b2 = new JButton("lookup");
        b3 = new JButton("remove");
        b4 = new JButton("exit");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        l1 = new JLabel("Host name:");
        l2 = new JLabel("IP address");
        t1 = new JTextField(25);
        t2 = new JTextField(25);
        t3 = new JTextField(25);

        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);

        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);

        p3.add(t3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        add(p1, BorderLayout.SOUTH);
        add(p3, BorderLayout.CENTER);
        add(p2, BorderLayout.NORTH);
        setVisible(true);
        setSize(800, 500);
        try {
            int serverport  = 1500;
            client = new Socket("localhost", serverport);
            System.out.println("Connected to server");
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                output.writeUTF("add");
                output.writeUTF(t1.getText());
                output.writeUTF(t2.getText());
                //obtains response from server
                String s = input.readUTF();
                t3.setText(s);
            }
            if (ae.getSource() == b2) {
                output.writeUTF("lookup");
                output.writeUTF(t1.getText());
                String s = input.readUTF();
                t3.setText(s);
            }
            if (ae.getSource() == b3) {
                output.writeUTF("remove");
                output.writeUTF(t1.getText());
                String s = input.readUTF();
                t3.setText(s);
            }
            if (ae.getSource() == b4) {
                System.exit(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new client2();
    }
}