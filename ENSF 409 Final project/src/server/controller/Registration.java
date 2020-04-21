
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
	
	/**
	 *completeRegistration completes a registration request from a student
	 * @param st is the student to which the registration is going to
	 * @param of is the course offering requested by the student
	 * @return an integer determining whether the registration was completed
	 */
	public int completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
	    return addRegistration();
	}
	
	/**
	 * addRegistration adds the registration request to the student's registartion list and
	 * the Course offerings registration list
	 * @return an integer determining whether or not the registration was completed correctly
	 */
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
	
	/**
	 * toString method that converts the registration to a string
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "\n--------------------------------\n";
		return st;
		
	}
	

}

