package client.view;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class GUI {

	
	PrintWriter socketOut;
	Socket aSocket;
	BufferedReader stdIn;
	BufferedReader socketIn;
	ObjectInputStream objectIn;

	public GUI(PrintWriter out, Socket socket, BufferedReader stdIn, BufferedReader In, ObjectInputStream objectIn) {
		this.socketOut = out;
		this.aSocket = socket;
		this.stdIn = stdIn;
		this.socketIn = In;
		this.objectIn = objectIn;
		
		MainFrame mainframe = new MainFrame();
		//other frames should be initialized inside the actionlisteners of other frame classes. 
	}
	
	public GUI() {
	//	MainFrame mainframe = new MainFrame();
		
	}
	
	
	//comment the main method once done. Just using for testing purposes right now. 
	
	public static void main(String[] args) {
		
		GUI g = new GUI();
		
	}
	
	
}
