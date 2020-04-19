package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import server.controller.Course;
import server.controller.CourseCatalogue;
import server.controller.DBManager;
import server.controller.Registration;
import server.controller.Student;

public class BackendServer implements Runnable{
	
	private Socket aSocket;
	private BufferedReader socketIn; 
	private PrintWriter socketOut;
	private ObjectOutputStream objectOut;
	private CourseCatalogue catalogue;
	private ArrayList<Student> studentList;
	
	private Student student; 
	
	public BackendServer(Socket aSocket, BufferedReader socketIn, PrintWriter socketOut, ObjectOutputStream objectOut) {
		this.aSocket = aSocket;
		this.socketIn = socketIn;
		this.socketOut = socketOut;
		this.objectOut = objectOut;
		
		catalogue = new CourseCatalogue();
		DBManager db = new DBManager();
		studentList = db.readStudentDataBase();
	}
	
	public void communicateWithClient() throws NumberFormatException, IOException{
		//first use of the Server - get an int from the client and then find and establish a student object. 
		while(true) {
			System.out.println("waiting for input...");
            int studID = Integer.parseInt(socketIn.readLine());        
            System.out.println("Student ID received: " + studID);
              
        //write the student object created in back end out to the client. 
            login(studID);
            Student st = getStudent();
            
            if(st == null) {
            	objectOut.writeObject(st);
            	socketOut.flush();
                continue;
            }
            else
            
            objectOut.writeObject(st);
            socketOut.flush();
            break;
        }
		
		do {
			
			int input = Integer.parseInt(socketIn.readLine());
					
			//different outputs depending on what the client sends back as an int from the gui commands? 
			switch(input) {
			
			//display all courses on catalogue
				case 1:
					socketOut.print(allCourses());
					break;				
			//view all taken courses 		
				case 2: 
					socketOut.println(viewAllTakenCourses());
					break;				
			//view registered courses
				case 3: 
					socketOut.println(viewRegCourses());
					break;					
			//if none of the cases, just go through loop again looking for some input. 		
				default: 
					continue;						
			}
			
		} while(true);
		
		
	}
	//login
	
	public void login(int id) {
		
		student = null;
		
		for(Student s : studentList) {
			
			if(s.getStudentId() == id)
				student = s;
			
		}
		
	}
		
	//views course
	
	public String allCourses() {
		
		return catalogue.toString();
		
	}
		
	//register courses 
	
	public void registerCourse(Course c) {
		
		Registration r = new Registration();
		r.completeRegistration(student, c.getCourseOfferingAt(0));
				
	}
	
	//view Registered courses
	
	public String viewRegCourses() {
		
		return student.viewRegisterdCourses();
		
	}
		
	public String viewAllTakenCourses() {
		
		return "empty for now - to be updated once we have a working database!";
		
	}
	
	public Student getStudent() {
		return student;
	}
	
	
	public void addCourseToStudentReg(String courseName, int secNum) {
		
		
		
		
	}
	@Override
	public void run() {
		try {
			communicateWithClient();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
