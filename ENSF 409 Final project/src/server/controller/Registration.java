
package server.controller;

import java.io.Serializable;

import server.controller.Student;

public class Registration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Student theStudent;
	private CourseOffering theOffering;
	
	public int completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
	    return addRegistration();
	}
	
	private int addRegistration () {
		int b = theStudent.addRegistration(this);;
		theOffering.addRegistration(this);
		System.out.println("the value of b: " + b);
		return b;
	}
	
	
	public Student getTheStudent() {
		return theStudent;
	}
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	
	@Override
	public String toString () {
		String st = "\n";
		st += getTheOffering();
		st += "\n--------------------------------\n";
		return st;
		
	}
	

}

