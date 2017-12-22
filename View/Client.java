package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Ryan: Always be sure to include useful comments in each file.
// Main Method For Client View
public class Client {

	public static void main(String args[]) 
	{
		String input = null;
		BufferedReader br  = null;
		
		System.setSecurityManager(new SecurityManager());
		
		SignupView signupVw = new SignupView();
		
		// Try-Catch 
		try {
		
			System.out.println("Welcome to IUPUI Shopping Center..");
		
			do{
				//User Input for Login or Register
				System.out.println("1.Login 2.Register 0.Quit");	
				br = new BufferedReader(new InputStreamReader(System.in));
				input = br.readLine();
				   	
				// based on input, Login or Register will be called.
				switch(input)
				{
					case "1" : 
								signupVw.userLogin();
								break;
					case "2" : 
								signupVw.RegisterUser();
								break;
					case "0" : 
							System.out.println("Exiting....");
							break;
					default:
						System.out.println("Invalid Selection....");		
				}		
				
			  } while (!input.equals("0"));
		} 
		catch(Exception e){
			System.out.println("Error: " + e.getMessage()); 
		}
		System.exit(0);
	}
}
