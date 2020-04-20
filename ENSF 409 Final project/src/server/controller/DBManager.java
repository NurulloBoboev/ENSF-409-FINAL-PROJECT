package server.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;


public class DBManager {
	
	ArrayList <Course> courseList;
	ArrayList<Student> studentList; 

	public DBManager () {
	
	}

	
	
	
	///
	public ArrayList<Student> readStudentDataBase(CourseCatalogue theCatalogue) {
		studentList = new ArrayList<Student>();
		
		
		try {
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "poggers");
			Statement statement = connection.createStatement();
			
			
			ResultSet resultSet = statement.executeQuery("select * from student");
			
			
			
			
			
			while(resultSet.next()) {
				
				
				Student st = new Student(resultSet.getNString("name"), resultSet.getInt("id"), resultSet.getInt("prereqkey"), resultSet.getInt("registeredcourseskey"));
				
				
				
				ResultSet resultSetPreReq = statement.executeQuery("select * from " +st.getPreReqKey() +"prereq");
				
				
				ArrayList<Course> preReqCourses = new ArrayList<Course>();
				
				while(resultSetPreReq.next()) {
					
					//search if course exists and if it does set it. 
					
					
					Course course = theCatalogue.searchCat(resultSetPreReq.getString("coursename"), resultSetPreReq.getInt("coursenum"));
					
					preReqCourses.add(course);
					
				
					
				}
				
				
				studentList.add(st);
				
				
				
				
			}
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		return studentList;
		
	}
	
	
	
	//READ ALL COURSES IN FIRST. -> THEN CAN ESSTABLISH THE STUDENT INFO BECASUE THE COURSES NOW EXIST IN THE CATALOGUE SO I CAN 
	// SEARCH FOR THEM AND ASSIGN THE OBJECT WHICH HAS ALREADY BEEN CONSTRUCTED
	
	
	
	public ArrayList readFromDataBase() {
		
		courseList = new ArrayList<Course>();
		
		try {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "poggers");
		Statement statement = connection.createStatement();
		
		
		
		ResultSet resultSet = statement.executeQuery("select * from allcourses");
		
		
		while(resultSet.next()) {
			
			Course c = new Course(resultSet.getString("coursename"), resultSet.getInt("coursenum"), resultSet.getString("preReqName"), resultSet.getInt("preReqNum"));
			courseList.add(c);
			
		}
		
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return courseList;
		
		
	}
	
	

}
