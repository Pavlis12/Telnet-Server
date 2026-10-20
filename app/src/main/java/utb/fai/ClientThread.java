package utb.fai;

import java.io.*;
import java.net.*;

public class ClientThread implements Runnable {
    private Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Implementation of processing incoming communication from the telnet client
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println("Received from client: " + line);
                writer.println(line);
            }
        }catch(Exception e){
              if(!this.clientSocket.isClosed()){
                e.printStackTrace();
            }

        }finally{
             try {
                this.clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client disconnected: " + this.clientSocket.getLocalAddress());

        }
    
    
    }
}
