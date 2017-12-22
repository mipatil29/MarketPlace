package Controller;

import View.AdminView;
import View.ShoppingView;
import ServerClass.Session;

public class Dispatcher {

	private ShoppingView shoppingVw;
	private AdminView adminVw;
	
	//Constructor to initialize the shopping and admin view
	public Dispatcher(){
		shoppingVw = new ShoppingView();	
		adminVw = new AdminView();
	}
	
	// Function to dispatch the view to their respective user
	public void dispatch(Session userSession)
	{	
		System.out.println("Dispatcher -> dispatch() -> " +  userSession.getUser().getRoleType());
		
		// checking user session, if user session role is admin then show the admin view or else show Shopping view
		if(userSession.getUser().getRoleType().equalsIgnoreCase("ADMIN"))
		{
			//passing user session to admin View
			adminVw.AdminInputView(userSession);
		}
		else
		{	
			//passing user session to Shopping View
			shoppingVw.UserShoppingView(userSession);
		}
	}
}
