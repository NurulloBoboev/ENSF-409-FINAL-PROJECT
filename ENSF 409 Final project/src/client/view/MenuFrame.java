package client.view;


import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

import client.controller.MenuFrameController;

import java.awt.*;


import server.controller.Student;

public class MenuFrame extends GUI{
	
	JFrame frame;
	
	
	public MenuFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn, Student student) 
	{
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
		this.student = student;
	}
	
	public MenuFrame()
	{
		
	}
	
	private JButton registerForCourseBut = new JButton("Register for a new course");
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JButton getRegisterForCourseBut() {
		return registerForCourseBut;
	}

	public void setRegisterForCourseBut(JButton registerForCourseBut) {
		this.registerForCourseBut = registerForCourseBut;
	}

	public JButton getViewAllCoursesBut() {
		return viewAllCoursesBut;
	}

	public void setViewAllCoursesBut(JButton viewAllCoursesBut) {
		this.viewAllCoursesBut = viewAllCoursesBut;
	}

	public JButton getViewRegisteredCoursesBut() {
		return viewRegisteredCoursesBut;
	}

	public void setViewRegisteredCoursesBut(JButton viewRegisteredCoursesBut) {
		this.viewRegisteredCoursesBut = viewRegisteredCoursesBut;
	}

	public JButton getLogoutBut() {
		return logoutBut;
	}

	public void setLogoutBut(JButton logoutBut) {
		this.logoutBut = logoutBut;
	}

	private JButton viewAllCoursesBut = new JButton("View all Courses");
	private JButton viewRegisteredCoursesBut  = new JButton ("View Registered Courses");
	private JButton logoutBut = new JButton ("Logout");
	
	public void displayFrame()
	{
		frame = new JFrame ("Student Menu");
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/6);
		frame.setLocationRelativeTo(null);
		
		JLabel welcomeMessage = new JLabel("	Welcome "+student.getStudentName()+"! ID: "+student.getStudentId(), SwingConstants.CENTER);
		welcomeMessage.setFont(new Font(welcomeMessage.getFont().toString(), Font.BOLD, 18));
		
		
		frame.add("Center", welcomeMessage);
		
		//frame.add("North", new JLabel("	Welcome "+student.getStudentName()+"! ID: "+student.getStudentId(), SwingConstants.CENTER));
		
		JPanel buttons = new JPanel(new FlowLayout());
		
		buttons.add(registerForCourseBut);
		buttons.add(viewAllCoursesBut);
		buttons.add(viewRegisteredCoursesBut);
		buttons.add(logoutBut);
		
		frame.add("South", buttons);
		frame.setVisible(true);

		MenuFrameController controller = new MenuFrameController(this);
        controller.runController();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*public static void main (String [] args)
	{
		MenuFrame mf = new MenuFrame();
		mf.displayFrame();
		

	}*/

	
}