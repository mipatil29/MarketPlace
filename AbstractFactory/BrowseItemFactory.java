package AbstractFactory;

import ServerClass.Session;
import Interface.IBrowseItems;

//Ryan: Always be sure to include useful comments in each file.
// Creating a Abstract Class BrowseItemFactory
public abstract class BrowseItemFactory {

	//Abstract Class of Interface Type 
	public abstract IBrowseItems BrowseFactory();
	
	//Method rceiving user input as User Session - to check which type of object has to be return
	public static BrowseItemFactory BrowseFactoryList(Session usersession)
	{
		BrowseItemFactory browseFactory =null;
		
		System.out.println("Abstract Factory  for " + usersession.getUser().getRoleType() + " Role.");
		
		// Checking if the user role is Admin or not
		if(usersession.getUser().getRoleType().equalsIgnoreCase("ADMIN"))
		{
			//creating a object of type  BrowseItemsAdminFactory
			browseFactory  = new BrowseItemsAdminFactory(usersession);
		}
		else
		{
			//creating a object of type  BrowseItemsCustomerFactory 
			browseFactory  = new BrowseItemsCustomerFactory(usersession);
		}
		
		return browseFactory;
	}

}
