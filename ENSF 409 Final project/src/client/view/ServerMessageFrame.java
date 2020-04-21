package client.view;

import javax.swing.*;

import client.controller.ServerMessageController;

import java.awt.*;

public class ServerMessageFrame {
	
	private String message;
	private JFrame frame;
	private JButton okBut = new JButton("OK");

	/**
	 * Constructor for the server message frame
	 * @param s is the message to be displayed
	 */
	public ServerMessageFrame(String s)
	{
		message = s;
	}
	
	/**
	 * display() displays the message to the screen via JFrame, whos "OK" button is controlled by
	 * a ServerMessageController
	 */
	public void display()
	{
		frame = new JFrame("Server Message");
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height/8);
		frame.setLocationRelativeTo(null);
		
		
		JLabel displayMessage = new JLabel(message);
		JPanel button = new JPanel(new FlowLayout());
		button.add(okBut);
		JPanel label = new JPanel(new FlowLayout());
		label.add(displayMessage);
		
		frame.add("Center", label);
		frame.add("South", button);
		
		frame.setVisible(true);
		ServerMessageController controller = new ServerMessageController(this);
		controller.runController();
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JButton getOkBut() {
		return okBut;
	}

	public void setOkBut(JButton okBut) {
		this.okBut = okBut;
	}

	
}
