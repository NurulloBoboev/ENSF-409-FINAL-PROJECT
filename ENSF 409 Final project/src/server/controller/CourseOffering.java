package server.controller;
import java.io.Serializable;
import java.util.ArrayList;

public class CourseOffering implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int secNum;
	private int secCap;
	private Course theCourse;
	//private ArrayList<Student> studentList;
	private ArrayList <Registration> offeringRegList;
	
	/**
	 * Constructor for a new CourseOffering
	 * @param secNum section/offering number
	 * @param secCap section/offering capacity
	 */

	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	public int getSecNum() {
		return secNum;
	}
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	public int getSecCap() {
		return secCap;
	}
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	public Course getTheCourse() {
		return theCourse;
	}
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	/**
	 * converts the CourseOffering to a string
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\n";
		//We also want to print the names of all students in the section
		return st;
	}
	/**
	 *addRegistration adds a students registration to the courseOffering
	 * @param registration is the registration to be added
	 */
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.add(registration);
		
	}
	
	
	
	

}
