import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {

    JLabel la = new JLabel("영어단어: ");
    JTextField tf = new JTextField(15);
    JButton btn = new JButton("검색");
    JLabel result = new JLabel("결과: ");

    Client(){
        setBounds(800, 100, 500, 500);
        setTitle("영단어 검색");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container c = getContentPane();
        JPanel p = new JPanel();
        p.setSize(500, 500);
        p.add(la);
        p.add(tf);
        p.add(btn);
        p.setBackground(Color.getHSBColor(100, 100, 100));

        c.add(p, BorderLayout.NORTH);
        c.add(result);
        c.setBackground(Color.getHSBColor(100, 200, 300));
        pack();

        System.out.println("Client is started");

        btn.addActionListener(e -> {
            try {
                Socket socket = new Socket("localhost", 5000);

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeUTF(tf.getText());

                result.setText(dis.readUTF());
                socket.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new Client();
    }
}
