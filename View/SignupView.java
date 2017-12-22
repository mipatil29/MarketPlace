package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

import Controller.FrontController;
import Controller.SignupController;
import Interface.ISignup;

//Ryan: Always be sure to include useful comments in each file.
//View for Login and Register
public class SignupView {
	
	SignupView signupVw = null;
	FrontController frntController = null;
	SignupController signupController = null;
	ISignup isignup;
	
	private String Username = null;
	private String Password = null;		
	private String Age = "0";
	private String DateOfBirth = null;

	// Constructor to initialize an interface
	public SignupView()
	{
		try {
			isignup = (ISignup)Naming.lookup("//in-csci-rrpc01.cs.iupui.edu:1990/SignupBind");
		}
		catch (Exception e) {
			displayMessages(e.getMessage());
		} 
	}
	
	// Login Method
	public void userLogin()
	{
		BufferedReader br  = null;
		// Try-Catch 
		try {
				signupVw = new SignupView();
				
				System.out.print("Enter UserName: ");	
				br = new BufferedReader(new InputStreamReader(System.in));
				Username = (br.readLine()).toUpperCase();
				
				System.out.println();
				
				System.out.print("Enter Password: ");	
				br = new BufferedReader(new InputStreamReader(System.in));
				Password = br.readLine().toUpperCase();

				System.out.println();
				
				// initializing a Front Controller object
				frntController = new FrontController(signupVw,isignup);
				
				//calling controller to check Authentication
				frntController.dispatchRequest(Username,Password);
		 } 
		 catch(Exception e){
			 displayMessages("Error : " + e.getMessage());
		 }
	}

	// Register Method to register a user
	public void RegisterUser()
	{
		BufferedReader br  = null;
		// Try-Catch 
		try {
			
			signupVw = new SignupView();
			signupController = new SignupController(signupVw,isignup);
			
			System.out.print("Enter UserName: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			Username = (br.readLine()).toUpperCase();
			
			System.out.print("Enter Password: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			Password = br.readLine().toUpperCase();  
			
			System.out.print("Enter Age: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			Age = br.readLine();  
			
			System.out.print("Enter Date Of Birth: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			DateOfBirth = br.readLine();  
				
			//Calling Login Function to check User status
			signupController.RegisterUser(Username,Password,Age,DateOfBirth);
			
		 } 
		 catch(Exception e){
			 displayMessages("Error : " + e.getMessage());
		 }
	}
	
	public void displayMessages(String message)
	{
		System.out.println(message);
	}
}
