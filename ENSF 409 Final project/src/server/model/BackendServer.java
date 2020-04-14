package server.model;

import java.util.ArrayList;

import server.controller.Course;
import server.controller.CourseCatalogue;
import server.controller.DBManager;
import server.controller.Registration;
import server.controller.Student;

public class BackendServer {

	private CourseCatalogue catalogue;
	private ArrayList<Student> studentList;
	
	
	private Student student; 
	
	public BackendServer(int id) {
		catalogue = new CourseCatalogue();
		DBManager db = new DBManager();
		studentList = db.readStudentDataBase();
		
		
		login(id);
		
		
		
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
	
	
	
}
