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
	
	public ViewAllCoursesFrame() throws IOException {
		displayFrame();
	}
	
	public ViewAllCoursesFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn) {
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
	}

	private JButton backBut = new JButton("Back to menu");
	
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

	public static void main(String [] args) throws IOException {
		ViewAllCoursesFrame yuh = new ViewAllCoursesFrame();
	}

	public void dispose() {
		// TODO Auto-generated method stub
		frame.dispose();
	}
}
