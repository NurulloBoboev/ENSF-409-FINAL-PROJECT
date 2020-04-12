package client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	
	private PrintWriter socketOut;
	private Socket aSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	
	
	public Client(String serverName, int portNum) {
		
		try {
			
			aSocket = new Socket(serverName, portNum);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);	
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void communicateServer() {
		
		// Do socket work here to connect to the server and send info back and forth.
	}
	
	
	
	public static void main(String[] args) {
		Client client = new Client("Local Host", 1000);
		client.communicateServer();

	}

}
