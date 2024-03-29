package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import server.controller.Course;
import server.controller.CourseCatalogue;
import server.controller.CourseOffering;
import server.controller.DBManager;
import server.controller.Registration;
import server.controller.Student;

public class BackendServer implements Runnable{
	
	private Socket aSocket;
	private BufferedReader socketIn; 
	private PrintWriter socketOut;
	private ObjectOutputStream objectOut;
	private CourseCatalogue catalogue;
	private ArrayList<Student> studentList;
	
	private Student student; 
	

	/**
	 * Constructor for BackenServer, initializes socket/stream information from the server,
	 * and creates new course/student database initializers
	 * @param aSocket Socket to be initialized as aSocket
	 * @param socketIn BufferedReader to be initialized as socketIn
	 * @param socketOut PrintWriter to be initialized as socketOut
	 * @param objectOut ObjectOutputStream to be initialized as objectOut
	 * @param sqlUserName SQL username to be used
	 * @param sqlPass SQL Password to be used
	 * @param sqlSchema SQL Schema to be used
	 */	
	public BackendServer(Socket aSocket, BufferedReader socketIn, PrintWriter socketOut, ObjectOutputStream objectOut, String sqlUserName, String sqlPass, String sqlSchema) {
		this.aSocket = aSocket;
		this.socketIn = socketIn;
		this.socketOut = socketOut;
		this.objectOut = objectOut;
		
		catalogue = new CourseCatalogue(sqlUserName, sqlPass, sqlSchema);
		DBManager db = new DBManager(sqlUserName, sqlPass, sqlSchema);
		studentList = db.readStudentDataBase(catalogue);
	}
	
	/**
	 * communicatewithClient communicates between the Server controller and client, processing
	 * client requests to the server controller via a switch
	 * @throws NumberFormatException relevant exception
	 * @throws IOException relevant exception
	 */
	public void communicateWithClient() throws NumberFormatException, IOException{
		//first use of the Server - get an int from the client and then find and establish a student object. 
		while(true) {
			System.out.println("waiting for input...");
            int studID = Integer.parseInt(socketIn.readLine());        
            System.out.println("Student ID received: " + studID);
              
        //write the student object created in back end out to the client. 
            login(studID);
            Student st = getStudent();
            
            if(st == null) {
            	objectOut.writeObject(st);
            	socketOut.flush();
                continue;
            }
            else
            
            objectOut.writeObject(st);
            socketOut.flush();
            break;
        }
		
		do {
			int input = Integer.parseInt(socketIn.readLine());
			//different outputs depending on what the client sends back as an int from the gui commands? 
			switch(input) {
			
			//display all courses on catalogue
				case 1:
					String allCoursesString = allCourses();
					socketOut.println(allCoursesString);
					socketOut.flush();
					break;				
			//view all taken courses 		
				case 2: 
					break;				
			//view registered courses
				case 3: 
					socketOut.println(viewRegCourses());
					socketOut.flush();
					break;	
				case 4:
					
					String name = socketIn.readLine();
					String num = socketIn.readLine();
					String section = socketIn.readLine();
					
					int cNum = Integer.parseInt(num);
					int sNum = Integer.parseInt(section);
					
					Course courseToAdd = catalogue.searchCat(name, cNum);
					
					if(courseToAdd == null)
					{
						socketOut.println("null");
						socketOut.flush();
					}
					else
					{
						int truth = registerCourse(courseToAdd, sNum);
						if(truth == 1) {
							socketOut.println("success");
							socketOut.flush();
						}
						else if(truth == 0 && student.getPreRegList().size() >= 6){
							socketOut.println("max");
							socketOut.flush();
						}
						else if(truth == 0) {
							socketOut.println("fail");
							socketOut.flush();
						}
						else if(truth == 2) {
							socketOut.println("dupe");
							socketOut.flush();
						}
					}
					break;					
				case 5:
					
			//if none of the cases, just go through loop again looking for some input. 		
				default: 
					continue;						
			}
			
		} while(true);
		
	}
	//login
	/**
	 * verifies login information from Login on the client side
	 * @param id is the login identification
	 */
	public void login(int id) {
		
		student = null;
		
		for(Student s : studentList) {		
			
			if(s.getStudentId() == id)
				student = s;
		}
	}
		
	//views course
	/**
	 * returns the catalogue in String form (View Courses)
	 * @return catalogue in String form
	 */
	public String allCourses() {
		
		return catalogue.toString();
		
	}
		
	//register courses 
	/**
	 * registers a student for a course(Register Student For Course)
	 * @param c is the course to be Registered
	 * @param offering is the offering to be registered in 
	 * @return whether the student was correctly registered or not
	 */

	public int registerCourse(Course c, int offering) {
		CourseOffering theOffering  = c.getCourseOffering(offering);
		Registration r = new Registration();
		return r.completeRegistration(student, theOffering);
	}
	
	//view Registered courses
	/**
	 * returns the students registered courses in String form (View Registered Courses)
	 * @return the students registered courses in String form
	 */

	public String viewRegCourses() {
		
		return student.viewRegisterdCourses();
		
	}
	
	
	public Student getStudent() {
		return student;
	}
	
	/**
	 * calls the communicateWithClient() method
	 */
	@Override
	public void run() {
		try {
			communicateWithClient();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Client closed the connection.");
		}
	}
	
	
	
}
