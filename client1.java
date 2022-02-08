import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class client1 extends JFrame implements ActionListener {
    JButton b1, b2;
    JLabel l1, l2, msg1, msg2;
    JPanel p1, p2, p3;

    public client1() {
        super("Application");
        b1 = new JButton("upload");
        b2 = new JButton("Download");

        l1 = new JLabel("Upload a file");
        l2 = new JLabel("Download a file");
        msg1 = new JLabel("");
        msg2 = new JLabel("");

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        p1.add(l1);
        p1.add(b1);
        p3.add(msg1);
        p2.add(l2);
        p2.add(b2);
        p3.add(msg2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        add(p1, BorderLayout.NORTH);
        add(p3, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        setVisible(true);
        setSize(300, 300);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (b1.getModel().isArmed()) {
                int serverport = 8020;
                Socket s = new Socket("localhost", serverport);
                System.out.println("CLIENT CONNECTED TO SERVER");

                JFileChooser j = new JFileChooser();
                j.showOpenDialog(client1.this);

                String filename = j.getSelectedFile().getName();
                String path = j.getSelectedFile().getPath();
                // sends data to server (like a container)
                PrintStream out = new PrintStream(s.getOutputStream());

                out.println("upload");
                out.println(filename);

                FileInputStream fis = new FileInputStream(path);
                // int n = fis.read();
                int ch;
                while ((ch = fis.read()) != -1) {
                    out.print((char) ch);
                    ch = fis.read();
                }
                fis.close();
                out.close();

                msg1.setText(filename + " is successfully uploaded");
                repaint();
            }
            if (b2.getModel().isArmed()) {
                Socket s = new Socket("localhost", 8020);
                System.out.println("CLIENT CONNECTED TO SERVER");
                String remoteadd = s.getRemoteSocketAddress().toString();
                System.out.println(remoteadd);
                JFileChooser j1 = new JFileChooser(remoteadd);
                j1.showOpenDialog(client1.this);
                String filename = j1.getSelectedFile().getName();
                String filepath = j1.getSelectedFile().getPath();
                PrintStream out = new PrintStream(s.getOutputStream());

                out.println("download");
                out.println(filepath);
                
                FileOutputStream fout = new FileOutputStream(filename);
                DataInputStream fromserver = new DataInputStream(s.getInputStream());
                int ch;
                while ((ch = fromserver.read()) != -1) {
                    fout.write((char) ch);
                }
                fout.close();
                msg2.setText(filename + "IS DOWNLOADED");
                repaint();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new client1();
    }
}