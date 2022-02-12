import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.net.*;
import java.io.*;

public class server3 extends JFrame implements ActionListener {
    JLabel label = new JLabel("Enter Ur Text :: ");
    JTextField text = new JTextField(20);
    JButton send = new JButton("SEND");
    JTextArea area = new JTextArea(20, 20);
    JScrollPane jsp = new JScrollPane(area);
    JPanel panel = new JPanel();
    
    ServerSocket server;
    Socket client;
    DataInputStream din;
    PrintWriter pw;

    public server3() {
        super("BAD BITCH WINDOW");
        panel.add(label);
        panel.add(text);
        panel.add(send);
        add(panel, BorderLayout.SOUTH);
        add(jsp);
        area.setEditable(false);
        send.addActionListener(this);
        text.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        try {
            int serverport = 7000;
            server = new ServerSocket(serverport);
            area.setText("Server Is Waiting For Client");
            client = server.accept();
            area.append("\nClient Is Now Connected");
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
                String temp = "\nClient : " + msg;
                area.append(temp);
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String msg = "\nServer : " + text.getText();
            area.append(msg);
            pw.println(text.getText());
            pw.flush();
            text.setText("");
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public static void main(String args[]) {
        new server3().receive();
    }
}