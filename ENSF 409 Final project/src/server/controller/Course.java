
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

	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		offeringList = new ArrayList<CourseOffering>();
	}

	
	
	public Course(String courseName, int courseNum, String preReqName, int preReqNum) {
		
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		offeringList = new ArrayList<CourseOffering>();
		
		this.preReqName = preReqName;
		this.preReqNum = preReqNum;
		
	}
	
	
	
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

	public CourseOffering getCourseOffering(int i) {

        for(CourseOffering o: offeringList) {
            
            if(o.getSecNum() == i) {
                return o;
            }
            
        }
              return null;
    }
}

