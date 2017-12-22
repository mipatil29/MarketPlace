package Model;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import Interface.*;
import ServerClass.Session;
import ServerClass.User;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;


public class Signup extends UnicastRemoteObject implements ISignup
{
	Session usersession = null;
	User user = null;
	
	public Signup() throws RemoteException {
		super();
	}

	// function to check Login status
	public Session Login(String Username, String Password) {
		
		System.out.println("---------------------------------------------------------");
		System.out.println("Login Function");
		
		Statement stmt = null;
		ResultSet rs = null;
		
		Connection conn = DatabaseConnection.getconnection();
		
		if(conn != null) {
			// try catch 
		
			try {
					stmt = (Statement) conn.createStatement();
					rs = stmt.executeQuery( "SELECT * FROM User Where Username = '" + Username + "' And Password = '" +  Password + "'");
					// checking User name and password with the stored data
				    if(rs.next())
			        { 
				       System.out.println(Username + " Logged in");
				       // create a session of customer type if user is a valid user
				       usersession = new Session(Username);
				       return usersession;			        	  
			        }
				}
				catch (Exception e) {
					System.out.println("Error: " + e.getMessage().toString());
					e.printStackTrace();
					return null;
				}
				return null;
		}
		else
		{
			return null;
		}
	}

	// Function to register a new user
	public  Session Registration(ArrayList<String> arrRegistration) {
	    
		System.out.println("---------------------------------------------------------");
		System.out.println("Registration Function");
		
		PreparedStatement PrepareStmt = null;
	
		try{
			 Connection conn = DatabaseConnection.getconnection();
			
			 String Username = arrRegistration.get(0);
			 String Password = arrRegistration.get(1);

			 // Insert Statement for registering USer
			 PrepareStmt =  (PreparedStatement) conn.prepareStatement("insert into User Values(?,?)");
			 PrepareStmt.setString(1, Username);
			 PrepareStmt.setString(2, Password);
		       
			 int i = PrepareStmt.executeUpdate();
				 
			 if (i > 0){
			          System.out.println(Username + " Resgistered Successfully");
			          usersession = new Session(Username);
			  		  return usersession;
			 }
			 else
			 {
					return null;
			 }
			 
	        }
			catch (Exception e)
			{
				System.out.println("Error: " + e.getMessage().toString());
				e.printStackTrace();
				return null;
			}
	}

	
}
