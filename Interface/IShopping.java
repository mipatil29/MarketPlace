// Interface for Shopping (To browse Item List and Place and order)
package Interface;

import java.rmi.Remote;
import java.util.ArrayList;
import ServerClass.Session;

//java annotations has been used, so that only Admin can access this Methods
public interface IShopping extends Remote {

	@RequiresRole(UserRole = "CUSTOMER")
	ArrayList<String> BrowseItems(Session usersession,String Productname) throws java.rmi.RemoteException;
	@RequiresRole(UserRole = "CUSTOMER")
	String PlaceOrder(Session usersession,String Product, String itemName, int Quantity, String OrderType) throws java.rmi.RemoteException;
	ArrayList<String> ConcurrencyBrowseItems(Session usersession,String Product) throws java.rmi.RemoteException;
	
}