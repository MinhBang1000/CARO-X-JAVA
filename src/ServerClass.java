import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ServerClass {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            Socket socket = serverSocket.accept();
            DataInputStream ips = new DataInputStream(socket.getInputStream());
            DataOutputStream ops = new DataOutputStream(socket.getOutputStream());
            Scanner kb = new Scanner(System.in);
            while (true){
                String str1 = ips.readUTF();
                if (str1.equals("q")){
                    break;
                }
                System.out.println("Client say: "+str1);
                String str2 = kb.nextLine();
                ops.writeUTF(str2);
                ops.flush();
            }
            socket.close();
            ips.close();
            ops.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
