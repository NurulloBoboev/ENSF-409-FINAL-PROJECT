package client.controller;

import java.awt.*;
import java.awt.event.*;

import client.view.RegisterForCourseFrame;

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
				String sendToServer = name+" "+num+" "+sec;
				register.getSocketOut().println(sendToServer);
				
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
