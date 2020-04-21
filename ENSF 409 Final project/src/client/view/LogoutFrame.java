package client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoutFrame {
	
	private MenuFrame menuFrame;
	
	/**
	 * LogoutFrame consructor
	 * @param f is the MenuFrame that the LogoutFrame stems from
	 */
	public LogoutFrame(MenuFrame f)
	{
		this.menuFrame = f;
	}
	
	public LogoutFrame()
	{
		
	}
	/**
	 * displays the LogoutFrame, which terminated shortly after by a System.exit(1) call from server
	 */
	public void display()
	{
		JFrame frame = new JFrame ("Logout Successful");
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/5, Toolkit.getDefaultToolkit().getScreenSize().height/10);
		frame.setLocationRelativeTo(null);
		JLabel message = new JLabel ("You have been successfully logged out");
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(message);
		frame.add("Center", panel);
		frame.setVisible(true);
	}
	
}
