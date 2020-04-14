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
	}

	
	
	public ArrayList<Student> readStudentDataBase() {
		
		studentList = new ArrayList<Student>();
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
		
		courseList = new ArrayList<Course>();
		
		Scanner textFileInputs = null; 
		
		String className;
		int classNum;
		
		//read from a basic dummy text file containing student names, numbers, etc. 
		try {
			textFileInputs = new Scanner(new File("CourseList.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		while(textFileInputs.hasNextLine()) {
			
			className = textFileInputs.next();
			classNum = Integer.parseInt(textFileInputs.next());
		
			
			Course c = new Course(className, classNum);
			
			courseList.add(c);
			
			
		}
		
	
		return courseList;
	}

}
