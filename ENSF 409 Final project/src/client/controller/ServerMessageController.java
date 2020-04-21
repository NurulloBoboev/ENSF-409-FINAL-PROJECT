package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ServerMessageFrame;

public class ServerMessageController {
	
	private ServerMessageFrame frame;
	
	/**
	 * constructor for class ServerMessageController
	 * @param f is the ServerMessageFrame that is to be controlled by the controller
	 */
	public ServerMessageController(ServerMessageFrame f)
	{
		this.frame = f;
	}
	
	/**
	 * runTheController handles all button actions from the ServerMessageFrame, and initiates calls to other methods if the 
	 * appropriate button is called (eg if the "OK" button is pressed, it deletes the frame)
	 */
	public void runController()
	{
		frame.getOkBut().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			frame.getFrame().dispose();
				
			}
		
		});
	}

}
