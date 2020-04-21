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
	
	/**
	 * Constructor for the GUI, which takes arguments from class client (Which has 
	 * communicated with the server and gotten relevant socket information [eg port number])
	 * @param out is the PrintWriter which will be used as a socketOut
	 * @param socket is the socket used for "aSocket"
	 * @param stdIn is the BufferedReader which will be used as stdIn
	 * @param In is the BufferedReader which will be used as a socketIn
	 * @param objectIn is the ObjectInputStream which will be used to take in necessary objects
	 */
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
	
	/**
	 * runGUI() is called form client and creates the login frame necessary to continue
	 * using the program, and gather necessary information about the user (Student id). The method also
	 * creates a MenuFrame instance if the login is successful, where the program will then primarily 
	 * be conducted from by the user
	 * @throws ClassNotFoundException relevant exception
	 * @throws IOException relevant exception
	 */
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

	public Socket getaSocket() {
		return aSocket;
	}

	public void setaSocket(Socket aSocket) {
		this.aSocket = aSocket;
	}

	public BufferedReader getStdIn() {
		return stdIn;
	}

	public void setStdIn(BufferedReader stdIn) {
		this.stdIn = stdIn;
	}

	public BufferedReader getSocketIn() {
		return socketIn;
	}

	public void setSocketIn(BufferedReader socketIn) {
		this.socketIn = socketIn;
	}

	public void setStudent(Student student) {
		this.student = student;
	}


}
