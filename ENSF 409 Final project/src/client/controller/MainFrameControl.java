package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.MainFrame;
import client.view.StudentInfoFrame;

public class MainFrameControl {

	public MainFrameControl(MainFrame mainFrame) {
			
		
		mainFrame.getOk().addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					
				System.out.println("OK in MainFrame pressed.");
				
				//opens the student info frame.
				StudentInfoFrame stFrame = new StudentInfoFrame();
				
			}
			
		});
		
		
	}
	
	
}
