package client.view;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

import client.controller.ViewAllCoursesController;

public class ViewAllCoursesFrame extends GUI{

	JFrame frame = new JFrame("Vew All Courses");
	String allcourses = "";
	private JButton backBut = new JButton("Back to menu");
	
	/**
	 * default constructor that displays all courses
	 * @throws IOException relevant exception
	 */
	public ViewAllCoursesFrame() throws IOException {
		displayFrame();
	}
	

	/**
	 * constructor for class ViewAllCoursesFrame, which initializes server communication variables from the GUI
	 * (and calls the super constructor to finalize the frame)
	 * @param socketOut is the printWriter to be used as a socketOut
	 * @param aSocket is the Socket to be used as aSocket
	 * @param stdIn is the BufferedReader to be used as stdIn
	 * @param socketIn is the BufferedReader to be used as a socketIn
	 * @param objectIn is the objectInputStream used to read in objects to the frame
	 */
	public ViewAllCoursesFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn) {
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
	}

	/**
	 * displayFrame() displays all courses in the course catalogue to a GUI that can be 
	 * viewed by the user, and is controlled by a ViewAllCoursesController, instantiated at the end
	 * of the method
	 * @throws IOException relevant exception
	 */
	public void displayFrame() throws IOException {
		System.out.println("View frame waiting for server input");
	
		while(socketIn.ready()) 
			allcourses+="\n"+socketIn.readLine();
		
		
	//	allcourses = socketIn.readLine();
		System.out.println("Input received: " + allcourses);
		
		JLabel label = new JLabel("List of All Courses");
		
		//Create scrolling pane to view courses
		JTextArea textArea = new JTextArea(allcourses);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(backBut);
		
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/4);
		frame.add(scrollPane);
		
		frame.add("North", label);
		frame.add("South", buttons);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		ViewAllCoursesController controller = new ViewAllCoursesController(this);
		controller.runController();
		
	}
	
	public JButton getBackBut() {
		return backBut;
	}

	public void setBackBut(JButton backBut) {
		this.backBut = backBut;
	}

	/**
	 * main function for the class
	 * @param args not used
	 * @throws IOException relevant exception
	 */
	public static void main(String [] args) throws IOException {
		ViewAllCoursesFrame yuh = new ViewAllCoursesFrame();
	}

	/**
	 * dispose() cancels/deletes the frame
	 */
	public void dispose() {
		// TODO Auto-generated method stub
		frame.dispose();
	}
}
