package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;

import AbstractFactory.BrowseItemFactory;
import ServerClass.Session;
import Controller.ShoppingController;
import Interface.IBrowseItems;
import Interface.IShopping;

//Ryan: Always be sure to include useful comments in each file.
// Shopping View for an Customer Functionality
public class ShoppingView {

	ShoppingView shopngVw = null;
	IShopping ishopng;
	ShoppingController shoppingController = null;
	IBrowseItems iBrowse = null;
	BrowseItemFactory browseItemFactory;
	
	private String product = null;
	private String itemName = null;
	private int quantity = 0;
	private String orderType = null;
	
	// Constructor to initialize an interface
	public ShoppingView()
	{
		try {
				ishopng = (IShopping)Naming.lookup("//in-csci-rrpc01.cs.iupui.edu:1990/ShopngBind");
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void UserShoppingView(Session usersession)
	{	
		BufferedReader br  = null;
		String input = null;
		
		try{
			// function to browse a product list
			do {  
				
				 shopngVw = new ShoppingView();
				 shoppingController = new ShoppingController(shopngVw,ishopng);
				 
				 
				 System.out.println();
				 System.out.println("1. Browse PrductList  2.PlaceOrder  3.Concurrency Test  0.Quit");
				 br = new BufferedReader(new InputStreamReader(System.in));
				 input = br.readLine();
				  
				  switch(input)
				  {
						case "1": 
									// logic to browsing product list
							     	System.out.print("Enter Product Category  (Main Menu: 'MEN' or 'Women'): ");
									br = new BufferedReader(new InputStreamReader(System.in));
									product = br.readLine().toUpperCase();
									
									System.out.println();
									
									// Implemented Abstract Factory to get the Shopping Object
									browseItemFactory = BrowseItemFactory.BrowseFactoryList(usersession); 
									iBrowse = browseItemFactory.BrowseFactory();
									
									// Calling Shopping Controller object with the help of returned object
									iBrowse.BrowseItems(product);
									break;
						case "2":
							
									// logic to place an order.
									System.out.print("Enter Product: ");	
									br = new BufferedReader(new InputStreamReader(System.in));
									product = (br.readLine()).toUpperCase();
									
									System.out.println();
								
									System.out.print("Enter ProductName: ");	
									br = new BufferedReader(new InputStreamReader(System.in));
									itemName = br.readLine().toUpperCase();
									
									System.out.println();
		
									System.out.print("Enter Quantity: ");	
									br = new BufferedReader(new InputStreamReader(System.in));
									quantity =  (Integer.parseInt(br.readLine()));
									
									System.out.println();
									
									System.out.print("Enter Order Type(Purchase/Return): ");	
									br = new BufferedReader(new InputStreamReader(System.in));
									orderType  = br.readLine().toUpperCase();
									
									// calling Controller Place order Function
									shoppingController.PlaceOrder(product,itemName,quantity,orderType,usersession);
									break;

						case "3" :
									shoppingController.ConcurrencyBrowseItems(usersession);
									break;
						case "0" : 
								System.out.println("Exiting....");
								break;		
					default:
								System.out.println("Invalid Selection....");
				  }
			} while (!input.equals("0"));
		}
		catch (Exception e) {
			displayMessages("Error Occured " + e.getMessage());
		}				
		
	}
	
	
	// Function To Display the Product List
	public void displayProductList(ArrayList<String> arrListItems){
		
		String[] strProductDesc;
		
		for (int i = 0; i <=0 ; i++) 
		{		
			strProductDesc = arrListItems.get(i).split("\\|", -1);
			if(strProductDesc.length == 1)
			{
				System.out.println("---------Product Category---------");
			}
			else
			{
				System.out.println("ItemName" + "\t" + "Price" + "\t" + "Quantity");
			}
		}
				
		for (int i = 0; i < arrListItems.size(); i++) 
		{		
			strProductDesc = arrListItems.get(i).split("\\|", -1);
			if(strProductDesc.length == 1)
			{
				System.out.println(strProductDesc[0]);
		    }
			else
			{
				System.out.println(strProductDesc[0] + "\t" + strProductDesc[1] + "\t" + strProductDesc[2]);
			}
		}
	}
    
	
	public void displayMessages(String message)
	{
		System.out.println(message);
	}
	
}
