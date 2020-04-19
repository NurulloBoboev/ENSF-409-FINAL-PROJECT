package client.controller;

import client.view.MenuFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuFrameController {
	
	MenuFrame frame;
	
	public MenuFrameController(MenuFrame f)
	{
		this.frame = f;
	}
	
	public void runController()
	{
		frame.getRegisterForCourseBut().addActionListener(new ActionListener(){
				@Override
			public void actionPerformed(ActionEvent e) {
				frame.getSocketOut().println("4");//This line is supposed to sned the switch case to 
				frame.getSocketOut().flush();     //the socket(Line 23)
			}
				});
		
		frame.getViewAllCoursesBut().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getSocketOut().println("1");
				frame.getSocketOut().flush();
			}
				});
		
		frame.getViewRegisteredCoursesBut().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getSocketOut().println("3");
				frame.getSocketOut().flush();
			}
		});
		
		frame.getLogoutBut().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getSocketOut().println("5");
				frame.getSocketOut().flush();
			}
		});
	}

}
