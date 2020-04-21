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
	
	/**
	 * Basic Student constructor
	 * @param studentName is the name of the student
	 * @param studentId is the ID of the student
	 */
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	
	/**
	 * Intensive Student constructor
	 * @param studentName is the name of the student
	 * @param studentId is the ID of the student
	 * @param preReqKey is the preReqKey of the student
	 * @param registeredCoursesKey is the registeredCoursesKey of the student
	 */
	public Student (String studentName, int studentId, int preReqKey, int registeredCoursesKey ) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
		
		this.preReqKey = preReqKey;
		this.registeredCoursesKey = registeredCoursesKey;
		preReqs = new ArrayList<Course>();
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
	/**
	 * student toString method
	 */
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}

	/**
	 * addRegistration adds a registration to the studentRegList, and checks to make sure that 
	 * all prerequisites are met and that there is enough capacity
	 * @param registration to be added to the student
	 * @return an integer determining whether or not the registration was completed
	 */
	public int addRegistration(Registration registration) {
		
		boolean preReqMet = false;
    
		if(registration.getTheOffering().getTheCourse().getPreReq() != null) {
            for(Course c: preReqs) {
                if(c.getCourseName().equals(registration.getTheOffering().getTheCourse().getPreReq().getCourseName())) {
                    preReqMet = true;    
                    break;
                    
                }
            }
        }
		
		if(registration.getTheOffering().getTheCourse().getPreReq() == null) {
	            preReqMet = true;
	        }
		  
		if(preReqMet == true && !studentRegList.contains(registration) && studentRegList.size() < 6 && notInCourseYet(registration) == false) {
			System.out.println("we POGGING boys.");
			studentRegList.add(registration);
			registration.getTheOffering().setSecCap(registration.getTheOffering().getSecCap() -1);
			DBManager.updateStudentRegistration(this, registration);
			return 1;
		} else if(preReqMet == true && !studentRegList.contains(registration) && studentRegList.size() < 6 && notInCourseYet(registration) == true){
			System.out.println("boolean is: " + notInCourseYet(registration));
			return 2;
		} else
			return 0;
	}

	//ONLY FOR ADDING FROM THE DATABASE
	/**
	 * updateRegistratio updates the studentRegList with a registration from the database
	 * @param registration is the registration to be added
	 */
	public void updateRegistration(Registration registration) {
		studentRegList.add(registration);	
		registration.getTheOffering().setSecCap(registration.getTheOffering().getSecCap() -1);
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
	
	/**
	 * notInCourseYet verifies whether or not the student is enrolled for a particular course
	 * @param reg is the registration to be referenced
	 * @return whether or not the student is enrolled in a course
	 */
	public boolean notInCourseYet(Registration reg) {

        String regName = reg.getTheOffering().getTheCourse().getCourseName() + reg.getTheOffering().getTheCourse().getCourseNum();

        for (Registration r: studentRegList) {

            String name = r.getTheOffering().getTheCourse().getCourseName() + r.getTheOffering().getTheCourse().getCourseNum();

            if(regName.equals(name))
            return true;
        }
        return false;
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
	
	public ArrayList<Registration> getPreRegList(){
		return studentRegList;
	}
	
}
