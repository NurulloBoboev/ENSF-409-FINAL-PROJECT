package client.controller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.view.RegisterForCourseFrame;

public class RegisterForCourseController {
	
	private RegisterForCourseFrame register;
	
	/**
	 * constructor for class RegisterForCourseController
	 * @param f is the RegisterForCourseFrame that is to be controlled by the controller
	 */
	public RegisterForCourseController(RegisterForCourseFrame f)
	{
		this.register = f;
	}
	
	/**
	 * runTheController handles all button actions from the RegisterForCourseFrame, and initiates calls to other methods if the 
	 * appropriate button is called (eg if "cancel" button is pressed, it deletes the frame) 
	 */
	public void runController()
	{
		register.getAddCourseBut().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = register.getCourseName().getText();
				String num = register.getCourseNum().getText();
				String sec = register.getSecNum().getText();
				
				if(name.isEmpty() || !Pattern.matches("[a-zA-Z]+", name)) {
					JOptionPane.showMessageDialog(new JFrame(), "Incorrect Course Name. Please enter a proper 4 character acronym.");
				}
				else if(num.isEmpty() || Pattern.matches("[a-zA-Z]+", num) || num.length() != 3) {
					JOptionPane.showMessageDialog(new JFrame(), "Incorrect input for Course Number. Please enter a 3 digit Course Number");
				}
				else if(!sec.equals("1") && !sec.equals("2")) {
					JOptionPane.showMessageDialog(new JFrame(), "Incorrect input for Section. Please enter a 1 digit Section Number.");
				}
				else {
				String sendToServer = name+"\n"+num+"\n"+sec;
				register.getSocketOut().println(sendToServer);
				
				try {
					String serverMessage = register.getSocketIn().readLine();
					
					if(serverMessage.equals("null")) {
						JOptionPane.showMessageDialog(new JFrame(), "The following course: " + name
								+ " " + num + "was not found.");
						register.getFrame().dispose();
					}
					else if(serverMessage.equals("success")){
						JOptionPane.showMessageDialog(new JFrame(), "You have successfully registered in: "
								+ name + " " + num + " Section " + sec);
						register.getFrame().dispose();
					}
					else if(serverMessage.equals("max")){
						JOptionPane.showMessageDialog(new JFrame(), "Course found but not added because you have exceeded"
								+ " the maximum number of registered courses.");
						register.getFrame().dispose();
					}
					else if(serverMessage.equals("fail")){
						JOptionPane.showMessageDialog(new JFrame(), "Course found, but you have not met the "
								+ "requirements for the course.");
						register.getFrame().dispose();
					}
					else if(serverMessage.equals("dupe")) {
						JOptionPane.showMessageDialog(new JFrame(), "Course found, but you have already"
								+ " registered for this course");
						register.getFrame().dispose();
					}
					
				//	ServerMessageFrame messageFrame  = new ServerMessageFrame(serverMessage);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
				
			}
		});
		
		register.getCancelBut().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				register.getFrame().dispose();
			}
		});
	}

}
