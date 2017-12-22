package AbstractFactory;

import java.rmi.Naming;

import Controller.ShoppingController;
import Interface.IBrowseItems;
import Interface.IShopping;
import View.ShoppingView;
import ServerClass.Session;

//Ryan: Always be sure to include useful comments in each file.
public class BrowseItemsCustomerFactory extends BrowseItemFactory{

	IBrowseItems browseItemCustomerShoppingFactory = null;
	ShoppingView shopngVw = null;
	IShopping ishopng;
	Session usersession;
	
	// Constructor for BrowseItemsCustomerFactory
	//Initialize the User Session and Shopping interface
	public BrowseItemsCustomerFactory(Session usersession){
		
		try {
				ishopng = (IShopping)Naming.lookup("//in-csci-rrpc01.cs.iupui.edu:1990/ShopngBind");
				this.usersession = usersession;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public IBrowseItems BrowseFactory() {
		
		System.out.println("Abstract Factory -> Browse Items -> Returning ShoppingView object");
		
		shopngVw = new ShoppingView();
		
		//Creating a ShoppingController object
		browseItemCustomerShoppingFactory = new ShoppingController(shopngVw,ishopng,usersession);
		
		// returning the ShoppingController object
		return browseItemCustomerShoppingFactory;
		
	}
}
