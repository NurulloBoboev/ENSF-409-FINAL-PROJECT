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
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
	
	
	private ArrayList<Course> preReqs;
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
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
		studentRegList.add(registration);
		System.out.println("Course successfully added!\n------------\n");
		for(int i = 0; i < studentRegList.size(); i++)
		{
			System.out.println(studentRegList.get(i).toString());
		}
	}

	
	public String viewRegisterdCourses() {
		
		return studentRegList.toString();
		
	}
	
}
