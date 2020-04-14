package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.controller.DBManager;
import server.controller.Student;

public class Server {

	private ServerSocket serverSocket;
	private Socket aSocket;
	private ExecutorService pool;
	private BufferedReader socketIn; 
	private PrintWriter socketOut;
	private ObjectOutputStream objectOut;
//	private ObjectInputStream objectIn;
	
	BackendServer theBackend;
	
	
	public Server(int portNum) 
	{		
		try {
			serverSocket = new ServerSocket(portNum);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
	}
	
	public void communicateWithClient(DBManager database) throws IOException
	{
		try 
		{
			aSocket = serverSocket.accept();
			System.out.println("Connection accepted by server");
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
		
			
			objectOut = new ObjectOutputStream(aSocket.getOutputStream());
			//objectIn = new ObjectInputStream(aSocket.getInputStream());
			
			
			
			//first use of the Server - get an int from the client and then find and establish a student object. 
			int studID = Integer.parseInt(socketIn.readLine());
			
			System.out.println("Student ID received: " + studID);
			
			theBackend = new BackendServer(studID);
			
			//write the student object created in back end out to the client. 
			objectOut.writeObject(theBackend.getStudent());
			socketOut.flush();
			do {
				
				
				int input = Integer.parseInt(socketIn.readLine());
				
				
				//different outputs depending on what the client sends back as an int from the gui commands? 
				switch(input) {
				
				//display all courses on catalogue
					case 1:
						socketOut.println(theBackend.allCourses());
						break;
					
				//view all taken courses 		
					case 2: 
						socketOut.println(theBackend.viewAllTakenCourses());
						break;
						
				//view registered courses
					case 3: 
						socketOut.println(theBackend.viewRegCourses());
						break;
						
				//if none of the cases, just go through loop again looking for some input. 		
					default: 
						continue;
				
				
				}
				
				
			
				
				
				
			} while(true);
					
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			pool.shutdown();
		}
	}

	public static void main(String[] args) 
	{
		Server server = new Server(1000);
		System.out.println("Server is now running!");
		DBManager database = new DBManager();
		try 
		{
			server.communicateWithClient(database);
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
}
