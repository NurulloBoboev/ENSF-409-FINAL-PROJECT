package client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.controller.ViewAllCoursesController;
import client.controller.ViewRegisteredController;

public class ViewRegisteredFrame extends GUI{
	
	JFrame frame = new JFrame("Vew All Registered Courses");
	String allcourses = "";
	
	public ViewRegisteredFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
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
		
		JLabel label = new JLabel("List of All Registered Courses");
		
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
		
		ViewRegisteredController controller = new ViewRegisteredController(this);
		controller.runController();
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String getAllcourses() {
		return allcourses;
	}

	public void setAllcourses(String allcourses) {
		this.allcourses = allcourses;
	}

	public JButton getBackBut() {
		return backBut;
	}

	public void setBackBut(JButton backBut) {
		this.backBut = backBut;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		frame.dispose();
	}
	
	
}
