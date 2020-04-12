package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	
	private ServerSocket serverSocket;
	private Socket aSocket;
	private ExecutorService pool;
	
	
	
	public Server(int portNum) {
		
		try {
			
			serverSocket = new ServerSocket(portNum);
			pool = Executors.newCachedThreadPool();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
		
	}
	
	
	
	public void communicateWithClient() throws IOException {
		
		try {
			
			///////Do socket work here to connect to the client. 
			
			
		} catch (Exception e) {
			e.printStackTrace();
			pool.shutdown();
			
		}
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		Server server = new Server(1000);
		System.out.println("Server is now running!");
		
		
		try {
			
			server.communicateWithClient();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		

	}

}
