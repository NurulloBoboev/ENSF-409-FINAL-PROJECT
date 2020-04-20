package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ServerMessageFrame;

public class ServerMessageController {
	
	private ServerMessageFrame frame;
	
	public ServerMessageController(ServerMessageFrame f)
	{
		this.frame = f;
	}
	
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
