package ServerClass;

import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import Interface.*;
import Model.*;

public class MarketPlaceServer extends UnicastRemoteObject 
{
	private static final long serialVersionUID = -5485952710128132535L;
	
	public MarketPlaceServer() throws RemoteException {
		super(); 
	}
	
	/* Main Method
	This method is responsible for starting the MarketPlace server and 
	binding it to the Java RMI lookup.
	*/
	public static void main(String args[]) {
		// Java RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		
		try{
			
			
			System.out.println("Creating a MarketPlace Server!");

			//Create a new instance of a MarketPlace (Shopping) server.
			String rmiBind = "//in-csci-rrpc01.cs.iupui.edu:1990/MarketPlaceServer";
			String SignupBind = "//in-csci-rrpc01.cs.iupui.edu:1990/SignupBind";
			String ShopngBind = "//in-csci-rrpc01.cs.iupui.edu:1990/ShopngBind";
			String AdminRoleBind = "//in-csci-rrpc01.cs.iupui.edu:1990/AdminRoleBind";

			MarketPlaceServer mrktPlace = new MarketPlaceServer();
			// creating proxy of a Signup class
			ISignup SignupRoleServerProxy = (ISignup) Proxy.newProxyInstance(ISignup.class.getClassLoader(),
								                new Class<?>[] {ISignup.class},
								                new AuthorizationInvocationHandler(new Signup()));

			// creating proxy of a AdminRole class
			IAdminRole adminRoleServerProxy = (IAdminRole) Proxy.newProxyInstance(IAdminRole.class.getClassLoader(),
							                	new Class<?>[] {IAdminRole.class},
							                	new AuthorizationInvocationHandler(new AdminRole()));
			
			// creating proxy of a Shopping Server class
			IShopping shoppingRoleServerProxy = (IShopping) Proxy.newProxyInstance(IShopping.class.getClassLoader(),
								                 new Class<?>[] {IShopping.class},
								                 new AuthorizationInvocationHandler(new Shopping()));

			// RMI Binding of a server proxy.
			Naming.rebind(rmiBind,mrktPlace);  
			Naming.rebind(SignupBind,SignupRoleServerProxy);
			Naming.rebind(ShopngBind,shoppingRoleServerProxy);
			Naming.rebind(AdminRoleBind,adminRoleServerProxy);
			
			System.out.println("Market Place Server Ready!");

		} 
		catch (Exception e){
			
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
