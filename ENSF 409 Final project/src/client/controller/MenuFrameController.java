package client.controller;

import client.view.MenuFrame;
import java.awt.*;

public class MenuFrameController {
	
	MenuFrame frame;
	
	public MenuFrameController(MenuFrame f)
	{
		this.frame = f;
	}
	
	public void runController()
	{
		frame.getRegisterForCourseBut().addActionListener(new ActionListener(){
				
			public void actionPerformed(ActionEvent e) {
				frame.getSocketOut().println("4");
				frame.getSocketOut().flush();
				
			}
				}
				);
	}

}
