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

import server.controller.Student;

public class LoginFrame extends GUI {
	
	JFrame frame;
	boolean tf = true;
	public LoginFrame() {
		displayFrame();
	}
	
	public LoginFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn) {
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
		
		// TODO Auto-generated constructor stub
	}

	public void displayFrame() {
		
		frame = new JFrame("Login Portal");
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/6);
		frame.setLocationRelativeTo(null);
		
		frame.add("North", new JLabel("Please enter your id", JLabel.CENTER));
		
		//textfield input
		JPanel textfield = new JPanel(new FlowLayout());
		JTextField userID = new JTextField("Student ID");
		userID.setPreferredSize(new Dimension(400,20));
		textfield.add(new JLabel("Student ID: "));
		textfield.add(userID);
		
		frame.add("West", textfield);
		
		//confirmation buttons
		JButton loginBut = new JButton("Login");
		JButton cancelBut = new JButton("Cancel");
		
		JPanel buttons = new JPanel(new FlowLayout());
		
		buttons.add(loginBut);
		buttons.add(cancelBut);
		
		frame.add("South", buttons);
		frame.setVisible(true);
		
		loginBut.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("did we crash?");
				
				socketOut.println(userID.getText());
				
				try {
					student = (Student) objectIn.readObject();
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				System.out.println("we didn't crash");
				
				if(userID.getText().isEmpty() || Pattern.matches("[a-zA-Z]+", userID.getText()) || userID.getText().length() > 5){
					JOptionPane.showMessageDialog(new JFrame(), "Incorrect input for Student ID. Please entera 5 digit ID");
				} else
					try {
						if(student == null){
							JOptionPane.showMessageDialog(new JFrame(), "Student with ID " + userID + " could not be found in our database. "
									+ "Please enter a different ID");
						}
						else {
							System.out.println(student.getStudentName());
							tf = false;
							frame.dispose();
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}});
		
		cancelBut.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					frame.dispose();
					
			}});
		
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public boolean getStatus() {
		// TODO Auto-generated method stub
		return tf;
	}
	
	//Uncomment to test LoginFrame on its own.
//	public static void main(String [] args) {
//		
//		LoginFrame f = new LoginFrame();
//	}
}
