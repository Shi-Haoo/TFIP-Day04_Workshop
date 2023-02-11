package revision.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//When we import this, it means all static fields&methods from Cookie class will be imported to
//this file. We can use Cookie class methods/field without using its class name. Example, 
//dont need to put Cookie.getRandomCookie(). Can just put .getRandomCookie() as seen in line 53

import static revision.server.Cookie.*;

public class ServerApp {
    
    public static void main( String[] args )
    {
        Socket sock = null;
        InputStream is = null;
        OutputStream os = null;
        

        if(args.length != 3){
            System.out.println("Please input port number, cookie.txt file path and file path where result is stored");
            System.exit(1);
        }
        
        try{
            ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
            System.out.printf("ServerPort no.: %d \n", Integer.parseInt(args[0]));

            String cookieFile = args[1];
            System.out.println(cookieFile);

            String cookieResultFile = args[2];
            System.out.println(cookieResultFile);

            while(true){
                sock = server.accept();
                is = sock.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                os = sock.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                while(true){
                    
                    try{ //try-catch EOF to close the server socket when client exit/quit the program
                        //Hence no more data cmg in resulting in EOF exception
                        String dataFromClient = dis.readUTF();
                    
                        if(dataFromClient.equals("get-cookie")){

                            String randomCookie = getRandomCookie(cookieFile,cookieResultFile);
    
                            dos.writeUTF("cookie-text_" + randomCookie);
                        } else{
                            dos.writeUTF("Invalid Comment");
                        }

                    }catch(EOFException e){
                        System.out.println("Client disconnected");
                        sock.close();
                        break;
                    }
                
                }

            }
        
        }catch(IOException e){
            e.printStackTrace();
        } 
            finally{
                try{
                    sock.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            
            }    
    }
}