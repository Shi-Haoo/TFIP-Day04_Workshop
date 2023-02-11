package revision.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) throws IOException{
        
        String[] arrSplit = args[0].split(":");

        while(true){
            Socket sock = new Socket(arrSplit[0], Integer.parseInt(arrSplit[1]));
            
            OutputStream os = sock.getOutputStream();
             DataOutputStream dos = new DataOutputStream(os);
             
             InputStream is = sock.getInputStream();
             DataInputStream dis = new DataInputStream(is);

             Console cons = System.console();

             String clientInput = cons.readLine("Send command to randomCookie server: ");

             dos.writeUTF(clientInput);
             dos.flush();

             if(clientInput.equals("get-cookie")){
                String cookie = dis.readUTF();
                String[] result = cookie.split("_");
                System.out.println("cookie from server is: " + result[1]);
             }
             else{
                System.err.println(dis.readUTF());
             }
             
             is.close();
             os.close();
             sock.close();

        }
}
}

