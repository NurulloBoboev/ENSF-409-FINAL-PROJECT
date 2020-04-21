
package server.controller;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private int courseNum;
	private Course preReq;
	private ArrayList<CourseOffering> offeringList;
	
	String preReqName;
	int preReqNum;

	/**
	 * Base Constructor for class course
	 * @param courseName is the name of the course to be created
	 * @param courseNum is the number of the course to be created
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		offeringList = new ArrayList<CourseOffering>();
	}

	
	
	/**
	 * In depth constructor for class course, initializing various necessary parameters
	 * @param courseName name of the course to be created
	 * @param courseNum number of the course to be created
	 * @param preReqName name of the necessary Pre-Requisite course
	 * @param preReqNum number of the necessary Pre-Requisite course
	 */
	public Course(String courseName, int courseNum, String preReqName, int preReqNum) {
		
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		offeringList = new ArrayList<CourseOffering>();
		
		this.preReqName = preReqName;
		this.preReqNum = preReqNum;
		
	}
	
	
	/**
	 * addOffering adds a particular course offering to the course
	 * @param offering is the offering to be added
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering);
		}
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	/**
	 * toStrong method that converts the course information and its offerings
	 * to a string
	 * retu
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c.toString();
		st += "\n-------------------------------------\n";
		return st;
	}

	public ArrayList<CourseOffering> getCourseOfferings() {
		return offeringList;
	}
	

	public Course getPreReq() {
		return preReq;
	}



	public void setPreReq(Course preReq) {
		this.preReq = preReq;
	}

	/**
	 * getCourseOffering returns the offering at a specified location in the list(offering/section number)
	 * @param i is the location in the list to return (offering/section number to search for)
	 * @return the course offering with the specific course offering
	 */
	public CourseOffering getCourseOffering(int i) {

        for(CourseOffering o: offeringList) {
            
            if(o.getSecNum() == i) {
                return o;
            }
            
        }
              return null;
    }
}

