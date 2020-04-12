package server.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//This class is simulating a database for our
//program
public class DBManager {
	
	ArrayList <Course> courseList;
	ArrayList<Student> studentList; 

	public DBManager () {
		courseList = new ArrayList<Course>();
	}

	
	
	public ArrayList readStudentDataBase() {
		Scanner textFileInputs = null; 
		
		String firstName, lastName;
		int id;
		
		//read from a basic dummy text file containing student names, numbers, etc. 
		try {
			textFileInputs = new Scanner(new File("StudentList.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		while(textFileInputs.hasNextLine()) {
			
			firstName = textFileInputs.next();
			lastName = textFileInputs.next();
			String name = firstName + " " + lastName;
			id = Integer.parseInt(textFileInputs.next());
			
			
			Student st = new Student(name, id);
			
			studentList.add(st);
			
			
		}
		
	
		return studentList;
	}
	
	
	
	
	public ArrayList readFromDataBase() {
		// TODO Auto-generated method stub
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		return courseList;
	}

}
