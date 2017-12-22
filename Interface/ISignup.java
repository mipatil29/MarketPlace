package Interface;

import java.rmi.*;
import java.util.ArrayList;
import ServerClass.Session;

// Interface for Signup
// Both the methods are returning Session

public interface ISignup extends Remote {
	
	public Session Registration(ArrayList<String> arrRegistration) throws java.rmi.RemoteException;
	public Session Login(String Username, String Password) throws java.rmi.RemoteException;
}
