package client.controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.view.LoginFrame;
import server.controller.Student;

public class LoginFrameController {

	LoginFrame frame;
	
	/**
	 * constructor of LoginFrameController 
	 * @param frame is the login frame that is to be controlled
	 */
	public LoginFrameController(LoginFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * runTheController handles all button actions from the login frame, and initiate calls to other 
	 * methods to continue the program
	 */
	public void runController() {
		frame.getLoginBut().addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(frame.getUserID().getText().isEmpty() || Pattern.matches("[a-zA-Z]+", frame.getUserID().getText()) || frame.getUserID().getText().length() != 5){
					JOptionPane.showMessageDialog(new JFrame(), "Incorrect input for Student ID. Please enter a 5 digit ID");
				} else {
					frame.getSocketOut().println(frame.getUserID().getText());
					frame.getSocketOut().flush();
					
					try {
						frame.setStudent((Student) frame.getObjectIn().readObject());
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						if(frame.getStudent() == null){
							JOptionPane.showMessageDialog(new JFrame(), "Student with ID " + frame.getUserID().getText() + " could not be found in our database. "
									+ "Please enter a different ID");
						}
						else {
							System.out.println(frame.getStudent().getStudentName());
							frame.setTf(false);
							frame.dispose();
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}});
		
		frame.getCancelBut().addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					frame.dispose();
					
			}});
	}
}
