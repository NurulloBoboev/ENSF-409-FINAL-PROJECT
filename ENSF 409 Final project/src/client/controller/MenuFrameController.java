package client.controller;

import client.view.MenuFrame;

import client.view.RegisterForCourseFrame;

import client.view.ViewAllCoursesFrame;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


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
				RegisterForCourseFrame register = new RegisterForCourseFrame(frame.getSocketOut(), frame.getaSocket(), frame.getStdIn(), frame.getSocketIn(), frame.getObjectIn(), frame.getStudent());
				frame.getSocketOut().println("4");
				register.displayFrame();		//This line is supposed to sned the switch case to 
				frame.getSocketOut().flush();     //the socket(Line 23)
			}
				});
		
		frame.getViewAllCoursesBut().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getSocketOut().println("1");
				frame.getSocketOut().flush();
				
				ViewAllCoursesFrame allCourses = new ViewAllCoursesFrame(frame.getSocketOut(), 
						frame.getaSocket(), frame.getStdIn(), frame.getSocketIn(), frame.getObjectIn());
				
				try {
					allCourses.displayFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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

