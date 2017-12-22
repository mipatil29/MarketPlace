// Interface for AdminRole
package Interface;

import java.rmi.Remote;
import java.util.ArrayList;
import ServerClass.Session;

// java annotations has been used, so that only Admin can access this Methods
public interface IAdminRole extends Remote {
	
	@RequiresRole(UserRole = "ADMIN")
	String AddItems(Session usersession,String parentPrductCategory,String product, String value) throws java.rmi.RemoteException;
	@RequiresRole(UserRole = "ADMIN")
	String RemoveItems(Session usersession,String Product, String itemName) throws java.rmi.RemoteException;
	@RequiresRole(UserRole = "ADMIN")
	String UpdateItems(Session usersession,String Product,String itemName, String value, String Type) throws java.rmi.RemoteException;
	@RequiresRole(UserRole = "ADMIN")
	ArrayList<String> BrowseItems(Session usersession,String Productname) throws java.rmi.RemoteException;
}