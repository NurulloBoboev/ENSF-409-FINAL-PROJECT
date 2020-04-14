package client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server.controller.Student;

public class GUI {

	private PrintWriter socketOut;
	Socket aSocket;
	BufferedReader stdIn;
	BufferedReader socketIn;
	private ObjectInputStream objectIn;
	Student student;
	public GUI(PrintWriter out, Socket socket, BufferedReader stdIn, BufferedReader In, ObjectInputStream objectIn) {
		
		this.setSocketOut(out);
		this.aSocket = socket;
		this.stdIn = stdIn;
		this.socketIn = In;
		this.setObjectIn(objectIn);
		
		
		//other frames should be initialized inside the actionlisteners of other frame classes. 
	}
	
	public GUI() {
		
	}
	
	public void runGUI() throws ClassNotFoundException, IOException {
		LoginFrame loginPortal = new LoginFrame(getSocketOut(), aSocket, stdIn, socketIn, getObjectIn());
		loginPortal.displayFrame();
		
		while(true) {
			if(loginPortal.getStatus()==false) {
				System.out.println("We out here");
				break;
			}
			System.out.print("");
		}
		
		
		student = loginPortal.getStudent();
		System.out.println("Name is: " + student.getStudentName());
		MenuFrame menu = new MenuFrame(getSocketOut(), aSocket, stdIn, socketIn, getObjectIn(), student);
		menu.displayFrame();
		if(aSocket.isClosed())
			System.exit(0);
		
		//MainFrame mainMenu = new MainFrame()

	}
	
	//comment the main method once done. Just using for testing purposes right now. 
	
//	public static void main(String[] args) {
//		
//		GUI g = new GUI();
//		
//	}
	
	public Student getStudent() {
		return this.student;
	}

	public PrintWriter getSocketOut() {
		return socketOut;
	}

	public void setSocketOut(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

	public ObjectInputStream getObjectIn() {
		return objectIn;
	}

	public void setObjectIn(ObjectInputStream objectIn) {
		this.objectIn = objectIn;
	}
	
}
