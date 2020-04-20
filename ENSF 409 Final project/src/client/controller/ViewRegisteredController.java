package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ViewRegisteredFrame;

public class ViewRegisteredController {

ViewRegisteredFrame frame;
	
	public ViewRegisteredController(ViewRegisteredFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

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
