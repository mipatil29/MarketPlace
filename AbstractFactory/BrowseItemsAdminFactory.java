package AbstractFactory;

import java.rmi.Naming;

import Controller.AdminRoleController;
import Interface.IAdminRole;
import Interface.IBrowseItems;
import ServerClass.Session;
import View.AdminView;


//Ryan: Always be sure to include useful comments in each file.
public class BrowseItemsAdminFactory extends BrowseItemFactory{

	IBrowseItems browseItemsAdminFactory = null;
	IAdminRole iadmin;
	AdminView adminVw = null;
	Session usersession = null;
	
	// Constructor for BrowseItemsAdminFactory
	//Initialize the User Session and admin interface
	public BrowseItemsAdminFactory(Session usersession){
		
		try {
				this.usersession = usersession;
				iadmin = (IAdminRole)Naming.lookup("//in-csci-rrpc01.cs.iupui.edu:1990/AdminRoleBind");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public IBrowseItems BrowseFactory() {
		
		System.out.println("Abstract Factory -> Browse Items -> Returning AdminRoleController object");
		
		adminVw = new AdminView();
		
		//Creating a AdminRoleController object
		browseItemsAdminFactory = new AdminRoleController(adminVw,iadmin,usersession);
		
		// returning the AdminRoleController object
		return browseItemsAdminFactory;
	}

}
