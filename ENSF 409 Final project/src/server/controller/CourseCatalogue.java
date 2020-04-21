package server.controller;
import java.util.ArrayList;

public class CourseCatalogue {
	
	private ArrayList <Course> courseList;
	
	/**
	 * constructor for CourseCatalog, which also calls method loadFromDataBase()
	 */
	public CourseCatalogue () {
		loadFromDataBase ();
	}
	
	/**
	 * loadFromDataBase creates a DBManager that will set the courseList in courseCatalogue,
	 * and also sets the prerequisite courses for each course in the catalogue
	 */
	private void loadFromDataBase() {
		// TODO Auto-generated method stub
		DBManager db = new DBManager();
		setCourseList(db.readFromDataBase());
		
		
		//sets pre reqs to existing course options
		for(Course c: courseList) {
			
			c.setPreReq(searchCat(c.preReqName, c.preReqNum));
			
		}
		
		
	}
	/**
	 * createCourseOffering creates and adds a course offering to a particular course in the
	 * catalogue
	 * @param c is the course to which will have a new offering
	 * @param secNum is the section number if the new offering
	 * @param secCap is the capacity of the new offering
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {

			CourseOffering theOffering = new CourseOffering(secNum, secCap);


			c.addOffering(theOffering);
		}
	}
	
	/**
	 * searchCat searches the courseCatalogue for a particular course by its name 
	 * and identifier (course Number)
	 * @param courseName is the name of the course to be found
	 * @param courseNum is the number/identifier of the course to be found
	 * @return the course if it is found in the catalogue
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}
	

	/**
	 * displays an error if searchCat() can not find a particular course
	 */
	private void displayCourseNotFoundError() {
		// TODO Auto-generated method stub
		System.err.println("Course was not found!");
		
	}
	public ArrayList <Course> getCourseList() {
		return courseList;
	}


	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	/**
	 * toString method that converts every ccourse in the catalogue to a string
	 */
	@Override
	public String toString () {
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		return st;
	}

}
