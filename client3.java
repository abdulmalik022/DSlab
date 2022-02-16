import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.net.*;
import java.io.*;

public class client3 extends JFrame implements ActionListener {
    JButton send = new JButton("SEND");
    JTextArea area = new JTextArea(20, 20);
    JScrollPane jsp = new JScrollPane(area);
    JTextField text = new JTextField(20);
    JLabel label = new JLabel("Enter Ur Text :: ");
    JPanel panel = new JPanel();

    Socket client;
    DataInputStream din;
    PrintWriter pw;

    public client3() {
        super("Client Window");
        panel.add(label);
        panel.add(text);
        panel.add(send);
        add(panel, BorderLayout.SOUTH);
        add(jsp);
        send.addActionListener(this);
        text.addActionListener(this);
        area.setEditable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        try {
            client = new Socket("localhost", 7000);
            pw = new PrintWriter(client.getOutputStream());
            din = new DataInputStream(client.getInputStream());
        } catch (Exception e) {
            System.out.println("\n\tException " + e);
        }
    }

    public void receive() {
        try {
            String msg;
            while ((msg = din.readLine()) != null) {
                String temp = "\nServer : " + msg;
                area.append(temp);
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String msg = "\nClient : " + text.getText();
            area.append(msg);
            pw.println(text.getText());
            pw.flush();
            text.setText("");
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public static void main(String args[]) {
        new client3().receive();
    }
}