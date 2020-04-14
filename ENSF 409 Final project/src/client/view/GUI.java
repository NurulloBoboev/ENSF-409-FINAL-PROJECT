package client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server.controller.Student;

public class GUI {

	PrintWriter socketOut;
	Socket aSocket;
	BufferedReader stdIn;
	BufferedReader socketIn;
	ObjectInputStream objectIn;
	Student student;
	public GUI(PrintWriter out, Socket socket, BufferedReader stdIn, BufferedReader In, ObjectInputStream objectIn) {
		
		this.socketOut = out;
		this.aSocket = socket;
		this.stdIn = stdIn;
		this.socketIn = In;
		this.objectIn = objectIn;
		
		
		//other frames should be initialized inside the actionlisteners of other frame classes. 
	}
	
	public GUI() {
		
	}
	
	public void runGUI() throws ClassNotFoundException, IOException {
		LoginFrame loginPortal = new LoginFrame(socketOut, aSocket, stdIn, socketIn, objectIn);
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
	
}
