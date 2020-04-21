package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.LoginFrameController;
import server.controller.Student;

public class LoginFrame extends GUI {
	
	JFrame frame;
	private boolean tf = true;
	private JButton loginBut = new JButton("Login");
	private JButton cancelBut = new JButton("Cancel");
	private JTextField userID = new JTextField("Student ID");
	
	public LoginFrame() {
		displayFrame();
	}
	
	/**
	 * constructor for class LoginFrame, which initializes server communication variables from the GUI
	 * (and calls the super constructor to finalize the LoginFrame)
	 * @param socketOut is the printWriter to be used as a socketOut
	 * @param aSocket is the Socket to be used as aSocket
	 * @param stdIn is the BufferedReader to be used as stdIn
	 * @param socketIn is the BufferedReader to be used as a socketIn
	 * @param objectIn is the objectInputStream used to read in objects to the frame
	 */
	public LoginFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn) {
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
		
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * displayFrame() is the method that creates and displays the login frame that is used to take
	 * user input (Student ID), and has buttons whose actions are managed by a LoginFrameController
	 */
	public void displayFrame() {
		
		frame = new JFrame("Login Portal");
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/6);
		frame.setLocationRelativeTo(null);
		
		frame.add("North", new JLabel("Please enter your id", JLabel.CENTER));
		
		//textfield input
		JPanel textfield = new JPanel(new FlowLayout());
		userID.setPreferredSize(new Dimension(400,20));
		textfield.add(new JLabel("Student ID: "));
		textfield.add(userID);
		
		frame.add("West", textfield);
		
		//confirmation buttons

		
		JPanel buttons = new JPanel(new FlowLayout());
		
		buttons.add(loginBut);
		buttons.add(cancelBut);
		
		frame.add("South", buttons);
		frame.setVisible(true);
		
		LoginFrameController controller = new LoginFrameController(this);
		controller.runController();
		
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTextField getUserID() {
		return userID;
	}

	public void setUserID(JTextField userID) {
		this.userID = userID;
	}

	public JButton getLoginBut() {
		return loginBut;
	}

	public void setLoginBut(JButton loginBut) {
		this.loginBut = loginBut;
	}

	public JButton getCancelBut() {
		return cancelBut;
	}

	public void setCancelBut(JButton cancelBut) {
		this.cancelBut = cancelBut;
	}

	public boolean getStatus() {
		// TODO Auto-generated method stub
		return isTf();
	}

	public void setStudent(Student student) {
		// TODO Auto-generated method stub
		this.student = student;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		frame.dispose();
	}

	public boolean isTf() {
		return tf;
	}

	public void setTf(boolean tf) {
		this.tf = tf;
	}
	

}
