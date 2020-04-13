package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends GUI {

	public LoginFrame() {
	}
	
	public void displayFrame() {
		
		JFrame frame = new JFrame("Login Portal");
		frame.setLayout(new BorderLayout());
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/6);
		frame.setLocationRelativeTo(null);
		
		frame.add("North", new JLabel("Please enter your id", JLabel.CENTER));
		
		//textfield input
		JPanel textfield = new JPanel(new FlowLayout());
		JTextField userID = new JTextField("Student ID");
		userID.setPreferredSize(new Dimension(400,20));
		textfield.add(new JLabel("Student ID: "));
		textfield.add(userID);
		
		frame.add("West", textfield);
		
		//confirmation buttons
		JButton loginBut = new JButton("Login");
		JButton cancelBut = new JButton("Cancel");
		
		JPanel buttons = new JPanel(new FlowLayout());
		
		buttons.add(loginBut);
		buttons.add(cancelBut);
		
		frame.add("South", buttons);
		frame.setVisible(true);
		
		loginBut.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(userID.getText().isEmpty() || Pattern.matches("[a-zA-Z]+", userID.getText()) || userID.getText().length() > 5){
					JOptionPane.showMessageDialog(new JFrame(), "Incorrect input for Student ID. Please entera 5 digit ID");
				}
//				else(objectIn.readObject() == null){
//					JOptionPane.showMessageDialog(new JFrame(), "Student with ID " + userID + " could not be found in our database. "
//							+ "Please enter a different ID");
//				}
				
			}});
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args) {
		LoginFrame f = new LoginFrame();
	}
}
