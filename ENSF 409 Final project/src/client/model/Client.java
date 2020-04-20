package client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.view.GUI;
/**
 * 
 * @author Nurullo Boboev, Trevor Brown, Andrew Lattimer
 *
 */
public class Client {

	
	private PrintWriter socketOut;
	private Socket aSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	private ObjectInputStream objectIn;
	
	public Client(String serverName, int portNum) {
		
		try {
			aSocket = new Socket(serverName, portNum);
		
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
			objectIn = new ObjectInputStream(aSocket.getInputStream());
				
		} catch(IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "Could not connect to server. Please try again.");
			System.exit(1);
		} 
		
	}
	
	public void communicateServer() throws ClassNotFoundException, IOException {
		
		// Do socket work here to connect to the server and send info back and forth.
		System.out.println("Communicate server got called");
		GUI GUI = new GUI(socketOut, aSocket, stdIn, socketIn, objectIn);
		System.out.println("new GUI instance succesfully created");
		GUI.runGUI();
		
	}
		
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Client client = new Client("localhost", 1000);
		client.communicateServer();

	}

}
