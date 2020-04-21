package server.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;


public class DBManager {
	
	ArrayList <Course> courseList;
	ArrayList<Student> studentList; 

	static String sqlUserName;
	static String sqlPass;
	static String sqlSchema;

	public DBManager (String sqlUserName, String sqlPass, String sqlSchema) {
		DBManager.sqlUserName = sqlUserName;
		DBManager.sqlPass = sqlPass;
		DBManager.sqlSchema = sqlSchema;

		
	}

	
	
	
	///
	public ArrayList<Student> readStudentDataBase(CourseCatalogue theCatalogue) {
		studentList = new ArrayList<Student>();
		
		
		try {
						
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sqlSchema, sqlUserName, sqlPass);

			Statement statement = connection.createStatement();
			
			
			ResultSet resultSet = statement.executeQuery("select * from student");
			
			
			
			
			
			while(resultSet.next()) {
				
				
				Student st = new Student(resultSet.getNString("name"), resultSet.getInt("id"), resultSet.getInt("prereqkey"), resultSet.getInt("registeredcourseskey"));
				
				
				studentList.add(st);
			}
			
		
			
			
			
			for(Student s : studentList) {
				
				ResultSet resultSetPreReq = statement.executeQuery("select * from " +s.getPreReqKey() +"prereq");
				
				
				ArrayList<Course> preReqCourses = new ArrayList<Course>();
				
				while(resultSetPreReq.next()) {
					
					//search if course exists and if it does set it. 
					Course course = theCatalogue.searchCat(resultSetPreReq.getString("coursename"), resultSetPreReq.getInt("coursenum"));
					
					preReqCourses.add(course);
					
				
				}
				
				
			}
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		setStudentRegisterdCourses(theCatalogue);
		
		return studentList;
		
	}
	
	
	
	//READ ALL COURSES IN FIRST. -> THEN CAN ESSTABLISH THE STUDENT INFO BECASUE THE COURSES NOW EXIST IN THE CATALOGUE SO I CAN 
	// SEARCH FOR THEM AND ASSIGN THE OBJECT WHICH HAS ALREADY BEEN CONSTRUCTED
	
	
	
	public ArrayList readFromDataBase() {
		
		courseList = new ArrayList<Course>();
		
		try {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sqlSchema, sqlUserName, sqlPass);
		Statement statement = connection.createStatement();
		
		
		
		ResultSet resultSet = statement.executeQuery("select * from allcourses");
		
		
		while(resultSet.next()) {
			
			Course c = new Course(resultSet.getString("coursename"), resultSet.getInt("coursenum"), resultSet.getString("preReqName"), resultSet.getInt("preReqNum"));
			courseList.add(c);
			
		}
		
		connection.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		updateCourseOfferings();
		
		return courseList;
	}
	
	
	public void updateCourseOfferings() {
		
		
	
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sqlSchema, sqlUserName, sqlPass);
				Statement statement = connection.createStatement();
				
				for(Course c: courseList) {
				
					String selectFrom = c.getCourseName().toLowerCase() + c.getCourseNum() + "offerings";
					
					ResultSet resultSet = statement.executeQuery("select * from " + selectFrom);
				
					while(resultSet.next()) {
						CourseOffering offering = new CourseOffering(resultSet.getInt("secnum"), resultSet.getInt("seccap"));
						c.addOffering(offering);
					}
				
				}
				connection.close();
				
				
			} catch (Exception e) {
				//e.printStackTrace();
			}

	}
	
	
	
	//made static such that a new DBManager object does not need to be constructed each time a 
	//student wants to update their registration
	public static void updateStudentRegistration(Student s, Registration r) {
		
		String tableName = s.getStudentId() + "registeredcourses";
		
		try {
      
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sqlSchema, sqlUserName, sqlPass);

			Statement statement = connection.createStatement();
			java.sql.DatabaseMetaData dbm = connection.getMetaData();
            ResultSet rs = dbm.getTables(null, null, tableName, null);
        
            	//fill existing table
            	String sql = "INSERT INTO " + tableName.toLowerCase() + " " +
        					 "VALUES ('" + r.getTheOffering().getTheCourse().getCourseName() + "', " + 
        		    		r.getTheOffering().getTheCourse().getCourseNum() + ", " + r.getTheOffering().getSecNum() + ", " +
        					 r.getTheOffering().getSecCap() +")";
        		    
        	    statement.executeUpdate(sql);
        	    connection.close();
                     
       
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	//made static such that a new DBManager object does not need to be constructed each time a 
	//student wants to view registration data
	
	public void setStudentRegisterdCourses(CourseCatalogue theCatalogue) {
		String tableString = "";
		
		try {
		
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sqlSchema, sqlUserName, sqlPass);
			Statement statement = connection.createStatement();	
			
			for(Student s: studentList) {
				tableString = s.getStudentId() + "registeredcourses";
				ResultSet resultSet = statement.executeQuery("select * from " + tableString.toUpperCase());
				
				while(resultSet.next()) {
					
					//search for course in course catalog and add it to the students registered courses if it exists. 
					Course c = theCatalogue.searchCat(resultSet.getString("coursename"), resultSet.getInt("coursenum"));
					
					//now traverse through the courses offerings to see if it matches an existing offering
					for(CourseOffering o: c.getCourseOfferings()) {
						//if the offering exists then add it to the student 
						if(o.getSecNum() == resultSet.getInt("secnum")) {
							Registration r = new Registration();
							r.setTheOffering(o);
							r.setTheStudent(s);
							s.updateRegistration(r);
						}
						
					}
					
				}
			
			}
		
			connection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
