package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ViewAllCoursesFrame;

public class ViewAllCoursesController {

	ViewAllCoursesFrame frame;
	
	/**
	 * constructor for the ViewAllCourses class
	 * @param frame is the ViewAllCoursesFrame to be controlled by the class
	 */
	public ViewAllCoursesController(ViewAllCoursesFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	/**
	 * runTheController handles all button actions from the ViewAllCoursesFrame, and initiates calls to other methods if the 
	 * appropriate button is called (eg if "Back" button is pressed, it deletes the frame)
	 */
	public void runController() {
		// TODO Auto-generated method stub
		frame.getBackBut().addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					frame.dispose();
			}});
		
	}
	
	

}
