package Controller;

import java.util.ArrayList;

import Interface.*;
import View.ShoppingView;
import View.SignupView;
import ServerClass.Session;

//SignupController 
public class SignupController {

	private SignupView signupVw;
	private ShoppingView shpngVw;
	ISignup isignup;
	Session usersession;
	
	//This constructor is used to initialize SignupView,ISignup
	public SignupController(SignupView signUpView,ISignup isgnup)
	{
		try {
			this.signupVw = signUpView;
			this.isignup = isgnup;
		} 
		catch (Exception e) {
			signupVw.displayMessages("Error : " + e.getMessage());
		}
	}
	
	// Register User Function
	public void RegisterUser(String Username, String Password, String Age, String DOB) {
	
		signupVw.displayMessages("SignupContoroller -> RegisterUser() ");
		
		//boolean registerflagStatus = false;
		ArrayList<String> lstRegisterUser = new ArrayList<String>();
		shpngVw = new ShoppingView();
		
		try {
			 
			// Adding User values to a list
			  lstRegisterUser.add(Username);
			  lstRegisterUser.add(Password);
			  lstRegisterUser.add(Age);
			  lstRegisterUser.add(DOB);

			  // Calling Server methods using RMI
			  usersession = isignup.Registration(lstRegisterUser);
			  
			  if(usersession != null){
				  // if user register successfully then passing it to Shopping view 
				  shpngVw.UserShoppingView(usersession);
			  }
			  else{
				  signupVw.displayMessages("Registration Failed..!");
			  }
		} 
		catch (Exception e) {
			signupVw.displayMessages("Error : " + e.getMessage());
		}
	}
}


