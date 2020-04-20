
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
	
	public void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration();
	}
	
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
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
		st += "The Offering: " + getTheOffering () + "\n";
		st += "\n--------------------------------\n";
		return st;
		
	}
	

}

