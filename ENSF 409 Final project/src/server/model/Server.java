package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.controller.DBManager;
import server.controller.Student;
/**
 * 
 * @author Nurullo Boboev, Trevor Brown, Andrew Lattimer
 *
 */
public class Server {

	private ServerSocket serverSocket;
	private Socket aSocket;
	private ExecutorService pool;
	private BufferedReader socketIn; 
	private PrintWriter socketOut;
	private ObjectOutputStream objectOut;
	
	private String sqlUserName;
	private String sqlPass;
	private String sqlSchema;
	
	BackendServer theBackend;
	
	/**
	 * Server constructor
	 * @param portNum port number of the Server Socket to be used
	 */
	public Server(int portNum) 
	{		
		try {
			serverSocket = new ServerSocket(portNum);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
	}
	
	/**
	 * communicateWithClient() communicates with client on the front end (establishes a connection to the client)
	 * and creates a backend instance that allows the client to complete actions via the backend server(eg to
	 * Register for a new course) 
	 * @throws IOException relevant exception
	 */
	public void communicateWithClient() throws IOException
	{
		try 
		{
			while(true) {
			aSocket = serverSocket.accept();
			System.out.println("Connection accepted by server");
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
			objectOut = new ObjectOutputStream(aSocket.getOutputStream());
			
			BackendServer backend = new BackendServer(aSocket, socketIn, socketOut, objectOut, sqlUserName, sqlPass, sqlSchema);
			pool.execute(backend);
			
			}
							
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			pool.shutdown();
		}
	}

	/**
	 * getSQLInformation retreives SQL information such username, password and schema
	 */
	public void getSQLInformation(){
		Scanner sc = new Scanner(System.in);		
		
		System.out.println("What is your sql username?");		
		sqlUserName = sc.nextLine();
		
		
		System.out.println("What is your sql password?");		
		sqlPass = sc.nextLine();
		
		System.out.println("What is your sql schema name?");
		sqlSchema = sc.nextLine();
		
		sc.close();
		
	}
	
	/**
	 * main function that runs server
	 * @param args not used
	 */
	public static void main(String[] args) 
	{
		
		Server server = new Server(1000);
		server.getSQLInformation();
		System.out.println("Server is now running!");
		try 
		{
			server.communicateWithClient();
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
}
