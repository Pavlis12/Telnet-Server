package utb.fai;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        int port = 12345;
        int max_threads = 10;    

        // Implement input parameter processing
        try{
            if(args.length < 2){
                System.out.println("Nedostatek parametru");
                return;
            }
            port = Integer.parseInt(args[0]);
            max_threads = Integer.parseInt(args[1]);
        }catch(Exception e){
            e.printStackTrace();
        }
        // Implementation of the main server loop
       
        try {
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService pool = Executors.newFixedThreadPool(max_threads);
          while(true) {
            Socket client = serverSocket.accept();
             pool.execute(new ClientThread(client));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
