package server.controller;
import java.io.Serializable;
import java.util.ArrayList;

import server.controller.Course;
import server.controller.Registration;

public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentName;
	private int studentId;
	
	private int preReqKey;
	private int registeredCoursesKey;
	private ArrayList<Registration> studentRegList;
	private ArrayList<Course> preReqs;
	
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	
	
	public Student (String studentName, int studentId, int preReqKey, int registeredCoursesKey ) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
		
		this.preReqKey = preReqKey;
		this.registeredCoursesKey = registeredCoursesKey;
		
	}
	
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}

	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub	
		//YALL NEED TO DO A POP UP OR ERROR HERE!!!
		
		boolean preReqMet = false;
    
		for(Course c: preReqs) {
			if(c.getCourseName().equals(registration.getTheOffering().getTheCourse().getCourseName())) {
				preReqMet = true;	
				break;
			}
		}
		
		if(preReqMet == true && !studentRegList.contains(registration)) {
			studentRegList.add(registration);
			DBManager.updateStudentRegistration(this, registration);
		} else 
			System.out.println("ERROR! Could not add the course. --> ADD AN ERROR SCREEN HERE PLEASE");
	}

	//ONLY FOR ADDING FROM THE DATABASE
	public void updateRegistration(Registration registration) {
		studentRegList.add(registration);	
	}
	
	public String viewRegisterdCourses() {
		
		return studentRegList.toString();
		
	}

	public int getPreReqKey() {
		return preReqKey;
	}

	public void setPreReqKey(int preReqKey) {
		this.preReqKey = preReqKey;
	}

	
	public void setPreReqs(ArrayList<Course> studentPreReqs) {
		
		this.preReqs = studentPreReqs; 
		
	}
	
	
	
	public int getRegisteredCoursesKey() {
		return registeredCoursesKey;
	}

	public void setRegisteredCoursesKey(int registeredCoursesKey) {
		this.registeredCoursesKey = registeredCoursesKey;
	}
	
	
	
	public String registeredCoursesData() {
		//LIKELY NEED TO FORMAT HERE
		return studentRegList.toString();
		
	}
	
	
}
