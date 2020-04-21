package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ViewRegisteredFrame;

public class ViewRegisteredController {

ViewRegisteredFrame frame;
	
/**
 * constructor for the ViewRegisteredController
 * @param frame is the ViewRegisteredFrame to be controlled by the controller
 */
	public ViewRegisteredController(ViewRegisteredFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	/**
	 * runTheController handles all button actions from the ViewRegisteredFrame, and initiates calls to other methods if the 
	 * appropriate button is called (eg if "back" button is pressed, it deletes the frame)
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
