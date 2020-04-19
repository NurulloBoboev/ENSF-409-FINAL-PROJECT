package client.view;

import java.awt.*;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class ViewAllCoursesFrame extends GUI{

	JFrame frame;
	
	public ViewAllCoursesFrame() {
		displayFrame();
	}
	

	public ViewAllCoursesFrame(PrintWriter socketOut, Socket aSocket, BufferedReader stdIn, BufferedReader socketIn,
			ObjectInputStream objectIn) {
		super(socketOut, aSocket, stdIn, socketIn, objectIn);
	}
	
	private void displayFrame() {
		
		
	}
}
