package client.view;

import java.awt.*;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;


import server.controller.Student;

public class RegisterForCourseFrame extends MenuFrame {
	
	public RegisterForCourseFrame()
	{
		
	}
	
	
	public RegisterForCourseFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn, Student student)
	{
		super(socketOut, aSocket, stdIn, socketIn, objectIn, student);
	}

	private JButton addCourseBut = new JButton("Register");
	private JButton cancelBut = new JButton("Cancel");
	private JTextField courseName = new JTextField();
	private JTextField courseNum = new JTextField();
	private JTextField secNum = new JTextField();
	private JLabel cName = new JLabel("Course Name");
	private JLabel cNum = new JLabel("Course Number");
	private JLabel sNum = new JLabel("Section");
	
	public JTextField getSecNum() {
		return secNum;
	}


	public void setSecNum(JTextField secNum) {
		this.secNum = secNum;
	}


	public JTextField getCourseName()
	{
		return courseName;
	}
	
	public JTextField getCourseNum()
	{
		return courseNum;
	}
	
	@Override
	public void displayFrame()
	{
		int h = 50;
		int w = 20;
		frame = new JFrame("Register for Course");
		frame.setLayout(new BorderLayout());
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		
		
		cName.setPreferredSize(new Dimension());
		cNum.setPreferredSize(new Dimension());
		sNum.setPreferredSize(new Dimension());
		courseName.setPreferredSize(new Dimension());
		courseNum.setPreferredSize(new Dimension());
		secNum.setPreferredSize(new Dimension());
		
	
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(addCourseBut);
		buttons.add(cancelBut);
		JPanel textFields = new JPanel();
		textFields.add(courseName);
		textFields.add(courseNum);
		textFields.add(secNum);
		JPanel texts = new JPanel();
		texts.add(cName);
		texts.add(cNum);
		texts.add(sNum);
		
		frame.add("South", buttons);
		frame.add("East", textFields);
		frame.add("West", texts);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JButton getAddCourseBut() {
		return addCourseBut;
	}

	public void setAddCourseBut(JButton addCourseBut) {
		this.addCourseBut = addCourseBut;
	}

	public JButton getCancelBut() {
		return cancelBut;
	}

	public void setCancelBut(JButton cancelBut) {
		this.cancelBut = cancelBut;
	}

	public void setCourseName(JTextField courseName) {
		this.courseName = courseName;
	}

	public void setCourseNum(JTextField courseNum) {
		this.courseNum = courseNum;
	}
	
	public static void main (String [] args)
	{
		RegisterForCourseFrame f = new RegisterForCourseFrame();
		f.displayFrame();
	}
	
}
