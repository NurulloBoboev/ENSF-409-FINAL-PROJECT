package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.LoginFrameController;
import server.controller.Student;

public class LoginFrame extends GUI {
	
	JFrame frame;
	private boolean tf = true;
	public LoginFrame() {
		displayFrame();
	}
	
	public LoginFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn) {
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
		
		// TODO Auto-generated constructor stub
	}
	
	private JButton loginBut = new JButton("Login");
	private JButton cancelBut = new JButton("Cancel");
	private JTextField userID = new JTextField("Student ID");

	
	public JTextField getUserID() {
		return userID;
	}

	public void setUserID(JTextField userID) {
		this.userID = userID;
	}

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
	
	//Uncomment to test LoginFrame on its own.
//	public static void main(String [] args) {
//		
//		LoginFrame f = new LoginFrame();
//	}
}
