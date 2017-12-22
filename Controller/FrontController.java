package Controller;

import Interface.ISignup;
import View.SignupView;
import ServerClass.Session;

public class FrontController {

	private Dispatcher dispatcher;
	public SignupView signupVw;
	ISignup isignup;
	Session usersession = null;
	
	//Constructor to initialize SignupView,ISignup  
	public FrontController(SignupView signUpView,ISignup isgnup) {
		try {
				this.signupVw = signUpView;
				this.isignup = isgnup;
				dispatcher = new Dispatcher();
		} 
		catch(Exception e){
				signupVw.displayMessages("Exception: " + e.getMessage()); 
		}
	}

	// Authenticate User 
	public boolean AuthenticateUser(String Username, String Password) {
		boolean loginflagStatus = false;
		try { 
			  signupVw = new SignupView();

			  System.out.println("Authenticating User :" + Username);
			  //RMI call to authenticate credentials
			  usersession = isignup.Login(Username, Password);
			  
			  if(usersession != null){
				  System.out.println(Username + ": Login Successful ");
				  loginflagStatus = true;  		
			  }
			  else{
				  loginflagStatus = false;
			  }
		} 
		catch (Exception e) {
			e.printStackTrace();
			loginflagStatus = false;
		}
		
		return loginflagStatus;
	 }

  	 // Dispatching the request to dispatcher class
	 public void dispatchRequest(String Username, String Password)
	 {
		 System.out.println("Front Controller -> dispatchRequest() ");
		 
		  //Calling Front controller Authenticate model
		 if(AuthenticateUser(Username,Password))
		 {
			 //Calling dispatcher dispatch method
			 dispatcher.dispatch(usersession);
		 }
		 else{
			 signupVw.displayMessages("Login Failed..!");
		 }
	 }
}
