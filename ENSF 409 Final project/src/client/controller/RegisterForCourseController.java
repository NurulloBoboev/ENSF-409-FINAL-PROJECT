package client.controller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
					String serverMessage = register.getSocketIn().readLine();
					ServerMessageFrame messageFrame  = new ServerMessageFrame(serverMessage);
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
