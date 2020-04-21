package client.view;

import java.awt.*;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

import client.controller.RegisterForCourseController;
import server.controller.Student;

public class RegisterForCourseFrame extends MenuFrame {
	
	public RegisterForCourseFrame()
	{
		
	}
	
	/**
	 * Constructor for class RegisterForFrame, which initializes server communication variables from the 
	 * MenuFrame (although it is called by its controller
	 * @param socketOut is the printWriter to be used as a socketOut
	 * @param aSocket is the Socket to be used as aSocket
	 * @param stdIn is the BufferedReader to be used as stdIn
	 * @param socketIn is the BufferedReader to be used as a socketIn
	 * @param objectIn is the objectInputStream used to read in objects to the frame
	 * @param student is the student to who the course is being for 
	 */
	public RegisterForCourseFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn, Student student)
	{
		super(socketOut, aSocket, stdIn, socketIn, objectIn, student);
	}

	private JButton addCourseBut = new JButton("Register");
	private JButton cancelBut = new JButton("Cancel");
	private JTextField courseName = new JTextField(14);
	private JTextField courseNum = new JTextField(14);
	private JTextField secNum = new JTextField(14);
	private JLabel cName = new JLabel("Course Name:");
	private JLabel cNum = new JLabel("Course Number:");
	private JLabel sNum = new JLabel("Section:");
	
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
	
	/**
	 * displayFrame() is the method that creates and displays the RegisteForCourseframe for the user, who
	 * can then input various parameters to determine which course to register for. The action of inputs and
	 * buttons are controlled/called by a RegisterForCourseController, which is instantiated at the end of
	 * the method
	 */
	@Override
	public void displayFrame()
	{
		frame = new JFrame("Register for Course");
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height/6);
		frame.setLocationRelativeTo(null);	
	
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(addCourseBut);
		buttons.add(cancelBut);
		
		JPanel textName = new JPanel(new FlowLayout());
		JPanel textNum = new JPanel(new FlowLayout());
		JPanel textSec = new JPanel(new FlowLayout());
		
		JPanel textFields = new JPanel(new GridLayout(3,2, 0, 3));

		cName.setHorizontalAlignment(JLabel.RIGHT);
		cNum.setHorizontalAlignment(JLabel.RIGHT);
		sNum.setHorizontalAlignment(JLabel.RIGHT);
		courseName.setHorizontalAlignment(JTextField.LEFT);
		
		textFields.add(cName);
		textName.add(courseName);
		textFields.add(textName);
		
		textFields.add(cNum);
		textNum.add(courseNum);
		textFields.add(textNum);
		
		textFields.add(sNum);
		textSec.add(secNum);
		textFields.add(textSec);
		frame.add("South", buttons);
		frame.add("West", textFields);

		frame.setVisible(true);
		RegisterForCourseController controller = new RegisterForCourseController(this);
        controller.runController();
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
