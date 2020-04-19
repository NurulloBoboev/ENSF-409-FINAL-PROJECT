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
	private JLabel cName = new JLabel("Course Name: ");
	private JLabel cNum = new JLabel("	Course Number: ");
	private JLabel sNum = new JLabel("	Section:");
	
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
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/4);
		frame.setLocationRelativeTo(null);
		
		
		cName.setPreferredSize(new Dimension(85, 20));
		cNum.setPreferredSize(new Dimension(100, 20));
		sNum.setPreferredSize(new Dimension(55, 20));
		courseName.setPreferredSize(new Dimension(80, 20));
		courseNum.setPreferredSize(new Dimension(80, 20));
		secNum.setPreferredSize(new Dimension(40, 20));
		
	
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(addCourseBut);
		buttons.add(cancelBut);
		JPanel textName = new JPanel(new FlowLayout());
		//JPanel textNum = new JPanel(new FlowLayout());
		//JPanel textSec = new JPanel(new FlowLayout());
		textName.add(cName);
		textName.add(courseName);
		textName.add(cNum);
		textName.add(courseNum);
		textName.add(sNum);
		textName.add(secNum);
		
		frame.add("South", buttons);
		frame.add(textName);
		//frame.add(textNum);
		//frame.add(textSec);
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
