import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 5000));

        System.out.println("Server is started");

        server.accept();

        Socket socket = server.accept();

        System.out.println("connected");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        // data 통신

        String str = dis.readUTF();  //문자열 읽기
        System.out.println(str);

        socket.close();
        server.close();
        System.out.println("Server is Terminated");

    }
}
