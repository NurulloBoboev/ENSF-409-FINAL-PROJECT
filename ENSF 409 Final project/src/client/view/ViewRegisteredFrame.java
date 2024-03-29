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
	
	/**
	 * constructor for class ViewRegisteredFrame, which initializes server communication variables from the GUI
	 * (and calls the super constructor to finalize the Frame)
	 * @param socketOut is the printWriter to be used as a socketOut
	 * @param aSocket is the Socket to be used as aSocket
	 * @param stdIn is the BufferedReader to be used as stdIn
	 * @param socketIn is the BufferedReader to be used as a socketIn
	 * @param objectIn is the objectInputStream used to read in objects to the frame
	 */
	public ViewRegisteredFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn) {
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
	}

private JButton backBut = new JButton("Back to menu");
	
	/**
	 * displayFrame() displays all courses that are registered by the user (Student) in a GUI
	 * interface for the user to browse, and whose button/input actions are controlled by a 
	 * ViewRegisteredController which is instantiated at the end of the method
	 * @throws IOException relevant exception
	 */
	public void displayFrame() throws IOException {

		while(true) {
			while(socketIn.ready()) 
				allcourses+="\n"+socketIn.readLine();
			if(!allcourses.isEmpty())
				break;
		}
		
		JLabel label = new JLabel("List of All Registered Courses");
		
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

	/**
	 * dispose() deletes the frame
	 */
	public void dispose() {
		// TODO Auto-generated method stub
		frame.dispose();
	}
	
	
}
