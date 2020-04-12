package client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class StudentInfoFrame {

	
	public StudentInfoFrame() {
		
		
		//displays the students info and has options to add courses, view courses, etc. 
		
		
		JFrame studInfoFrame = new JFrame("Student Info");
		studInfoFrame.setLayout(new BorderLayout());
		studInfoFrame.setSize(600, 400);
		studInfoFrame.setLocationRelativeTo(null);
		
		
		
		studInfoFrame.setVisible(true);
		
	}
	
	
}
