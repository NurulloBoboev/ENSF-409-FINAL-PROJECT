package client.controller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.view.RegisterForCourseFrame;
import client.view.ServerMessageFrame;

public class RegisterForCourseController {
	
	private RegisterForCourseFrame register;
	
	public RegisterForCourseController(RegisterForCourseFrame f)
	{
		this.register = f;
	}
	
	public void runController()
	{
		register.getAddCourseBut().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = register.getCourseName().getText();
				String num = register.getCourseNum().getText();
				String sec = register.getSecNum().getText();
				String sendToServer = name+"\n"+num+"\n"+sec;
				register.getSocketOut().println(sendToServer);
				
				try {
					System.out.println("we out here");
					String serverMessage = register.getSocketIn().readLine();
					System.out.println("pogpogpogopgpogpo");
					
					if(serverMessage.equals("null")) {
						JOptionPane.showMessageDialog(new JFrame(), "The following course: " + name
								+ " " + num + "was not found.");
						register.getFrame().dispose();
					}
					else if(serverMessage.equals("success")){
						System.out.println(serverMessage);
						JOptionPane.showMessageDialog(new JFrame(), "You have successfully registered in: "
								+ name + " " + num + " Section " + sec);
						register.getFrame().dispose();
					}
					else if(serverMessage.equals("fail")){
						System.out.println(serverMessage);
						JOptionPane.showMessageDialog(new JFrame(), "Course found, but you have not met the "
								+ "requirements for the course.");
						register.getFrame().dispose();
					}
					
				//	ServerMessageFrame messageFrame  = new ServerMessageFrame(serverMessage);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
