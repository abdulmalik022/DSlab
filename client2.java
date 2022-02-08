
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class client2 extends Frame implements ActionListener {
    Button b1, b2, b3, b4;
    Panel p1, p2, p3;
    Label l1, l2;
    TextField t1, t2, t3;
    Socket s1;
    String s;
    DataOutputStream output;
    DataInputStream input;

    client2() {
        super("client2");
        b1 = new Button("add");
        b2 = new Button("lookup");
        b3 = new Button("remove");
        b4 = new Button("exit");
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        l1 = new Label("Host name:");
        l2 = new Label("IP address");
        t1 = new TextField(" ", 25);
        t2 = new TextField(" ", 25);
        t3 = new TextField(" ", 25);
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
        add("South", p1);
        add("Center", p3);
        add("North", p2);
        setVisible(true);
        setSize(800, 500);
        try {
            Socket s1 = new Socket("localhost", 1500);
            if (s1 != null)
                System.out.println("Connected to server");
            output = new DataOutputStream(s1.getOutputStream());
            input = new DataInputStream(s1.getInputStream());
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
                s = input.readUTF();
                t3.setText(s);
            }
            if (ae.getSource() == b2) {
                output.writeUTF("lookup");
                output.writeUTF(t1.getText());
                s = input.readUTF();
                t3.setText(s);
            }
            if (ae.getSource() == b3) {
                output.writeUTF("remove");
                output.writeUTF(t1.getText());
                s = input.readUTF();
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
        client2 dc = new client2();
    }
}