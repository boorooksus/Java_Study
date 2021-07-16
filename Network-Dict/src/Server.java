import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Server {

    static Map<String, String> db = new HashMap<String, String>();
    static boolean bRunning = true;

    public static void main(String[] args) throws IOException {

        db.put("apple", "사과");
        db.put("banana", "바나나");
        db.put("cat", "고양이");

        ServerSocket server = new ServerSocket(5000);

        System.out.println("Server is running...");

        while(bRunning){
            Socket socket = server.accept();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // protocol
            String str = dis.readUTF();

            if(str.equals("exit")){
                dos.writeUTF("bye");
                socket.close();
                break;
            }

            String result = db.get(str);

            // 등록되지 않은 단어인 경우 "Not Found" 전송
            dos.writeUTF(Optional.ofNullable(result).orElse("Not Found"));

            socket.close();
        }

        server.close();
        System.out.println("Server is terminated");
    }
}
