package client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import client.controller.MainFrameControl;


public class MainFrame {

	//MAKE BUTTONS AND ANYTHING THAT NEEDS ACTION LISTENERS MEMBER VARIABLES. 
	private JButton ok = new JButton("Ok");
	
	
	public MainFrame() {
		
		JFrame mainFrame = new JFrame("Main Window");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		mainFrame.setLocationRelativeTo(null);
        
        mainFrame.add("North", new JLabel("What is the students name and ID number? ", SwingConstants.CENTER));
        
        mainFrame.add("South", ok);
        
        //insert a text box to get user input and then an okay button. 
        

        //set the action listeners for this frame.
		MainFrameControl control = new MainFrameControl(this);
		
        
		mainFrame.setVisible(true);
		
        
		
	}
	
	
	public JButton getOk() {
		return ok;
	}
	
	
	
	
}
