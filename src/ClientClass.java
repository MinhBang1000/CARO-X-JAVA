import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ClientClass {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost",3333);
            DataInputStream ips = new DataInputStream(socket.getInputStream());
            DataOutputStream ops = new DataOutputStream(socket.getOutputStream());
            while (true){
                String str2 = kb.nextLine();
                ops.writeUTF(str2);
                ops.flush();
                if (str2.equals("q")){
                    break;
                }
                String str1 = ips.readUTF();
                System.out.println("Server say: " + str1);
            }
            socket.close();
            ips.close();
            ops.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
