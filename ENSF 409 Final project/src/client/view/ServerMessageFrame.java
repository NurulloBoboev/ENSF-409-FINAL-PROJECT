package client.view;

import javax.swing.*;

import client.controller.ServerMessageController;

import java.awt.*;

public class ServerMessageFrame {
	
	private String message;
	private JFrame frame;
	private JButton okBut = new JButton("OK");

	public ServerMessageFrame(String s)
	{
		message = s;
	}
	
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
	
	public static void main(String [] args)
	{
		ServerMessageFrame sm = new ServerMessageFrame("Course added successfully");
		sm.display();
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
