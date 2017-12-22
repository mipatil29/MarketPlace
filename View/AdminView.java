package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;

import AbstractFactory.BrowseItemFactory;
import Command.AdminRoleCommandInvoker;
import Controller.AdminProperties;
import Controller.AdminRoleController;
import ServerClass.Session;
import Interface.IAdminRole;
import Interface.IBrowseItems;

//Ryan: Always be sure to include useful comments in each file.
// View of an Admin Functionality
public class AdminView {

	AdminView adminVw = null;
	IAdminRole iadmin;
	AdminRoleController adminController = null;
	AdminRoleCommandInvoker adminCommandInvoker = null;
	IBrowseItems iBrowse = null;
	BrowseItemFactory browseItemFactory;
	
	private String adminInput = null;
	private String adminUpdateInput = null;

	private String product = null;
	private String prodDesc = null;
	
	BufferedReader br = null;

	// Constructor to initialize an interface
	public AdminView()
	{
		try {
			iadmin = (IAdminRole)Naming.lookup("//in-csci-rrpc01.cs.iupui.edu:1990/AdminRoleBind");
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// AdminInputView having User session
	public void AdminInputView(Session usersession)
	{	
		// try catch
		try {
	
			
			 adminVw = new AdminView();
			 AdminProperties adminClass = new AdminProperties();
			 
			 adminController = new AdminRoleController(adminVw,iadmin,adminClass,usersession);
			 // initializing a AdminRoleCommandInvoker
			 adminCommandInvoker = new AdminRoleCommandInvoker(adminController);
			  
			 do{
				 			System.out.println();
				 			System.out.print("Choose Your Option: ");	
							System.out.println("1.Add  2.Update  3.Remove  4.ProductList 0.Quit");	
							br = new BufferedReader(new InputStreamReader(System.in));
							adminInput = br.readLine();
							String strProduct_Item_input = null;
							
							//Add, Update and Remove Functionality (Admin Input)
							switch(adminInput)
							{
								case "1": 
											// Adding a product category or Item in a category 
											System.out.print("Do you want to add new Product Category (Y/N)?: ");	
											br = new BufferedReader(new InputStreamReader(System.in));
											strProduct_Item_input  = ((br.readLine()).toUpperCase());
											 
											// if else to add a new product category or Product
											if(strProduct_Item_input.equalsIgnoreCase("Y") || strProduct_Item_input.equalsIgnoreCase("YES"))
											{
											   System.out.print("Enter Parent Product Category:");	
											   br = new BufferedReader(new InputStreamReader(System.in));
											   adminClass.SetParentPrductCategory((br.readLine()).toUpperCase());
											   
											    System.out.println();

											    System.out.print("Enter New Category : ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												adminClass.Setproduct((br.readLine()).toUpperCase());
												
												adminClass.SetProdDesc(null);
											}	
											else
											{
												System.out.print("Enter Product Category: ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												adminClass.Setproduct((br.readLine()).toUpperCase());
												
												System.out.println();
													
												System.out.print("Enter ItemName: ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												prodDesc = (br.readLine()).toUpperCase();
												
												System.out.println();
												
												System.out.print("Enter Price: ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												prodDesc = prodDesc + "|" + br.readLine();
												
												System.out.println();
												
												System.out.print("Enter Quantity: ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												prodDesc = prodDesc + "|" + br.readLine();
												
												adminClass.SetProdDesc(prodDesc);
												
												adminClass.SetParentPrductCategory(null);
											}
											
											//Calling AdminRole Command Invoker to call server additems function
											adminCommandInvoker.execute("Add");
											
											break;
								case "2": 
											do {
												   // Updating a product Amount or Quantity 
													System.out.println("1.Price  2.Quantity  0.Quit");
													br = new BufferedReader(new InputStreamReader(System.in));
													adminUpdateInput = br.readLine();

													// Switch Statement to Update a Price or Quantity of a Product
													switch(adminUpdateInput)
													{
														case "1":
																System.out.print("Enter Product: ");	
																br = new BufferedReader(new InputStreamReader(System.in));
																adminClass.Setproduct((br.readLine()).toUpperCase());
																
																System.out.println();

		 													    System.out.print("Enter ProductName: ");	
																br = new BufferedReader(new InputStreamReader(System.in));
																adminClass.SetItemName((br.readLine()).toUpperCase());
																
																System.out.println();

																System.out.print("Enter Price: ");	
																br = new BufferedReader(new InputStreamReader(System.in));
																adminClass.SetProdDesc(br.readLine());
																
																adminClass.SetUpdateType("PRICE");
																
																//Calling AdminRole Command Invoker to call server Updateitems function
																adminCommandInvoker.execute("Update");
																
																break;
														case "2":
															
																System.out.print("Enter Product: ");	
																br = new BufferedReader(new InputStreamReader(System.in));
																adminClass.Setproduct((br.readLine()).toUpperCase());
																
																System.out.println();
															
																System.out.print("Enter ProductName: ");	
																br = new BufferedReader(new InputStreamReader(System.in));
																adminClass.SetItemName((br.readLine()).toUpperCase());
																
																System.out.println();

																System.out.print("Enter Quantity: ");	
																br = new BufferedReader(new InputStreamReader(System.in));
																adminClass.SetProdDesc(br.readLine());
																
																adminClass.SetUpdateType("QUANTITY");
																
																//Calling AdminRole Command Invoker to call server Updateitems function
																adminCommandInvoker.execute("Update");
																
																break;
														case "0" : 
															System.out.println("Exiting....");
															break;		
														default:
															System.out.println("Invalid Selection....");
													}
												}while (!adminUpdateInput.equals("0"));
											break;
								case "3": 
									
											System.out.print("Do you want to Remove Product Category (Y/N): ");	
											br = new BufferedReader(new InputStreamReader(System.in));
											strProduct_Item_input  = ((br.readLine()).toUpperCase());

											//If else to remove a Product or or a Product Category
											if(strProduct_Item_input .equalsIgnoreCase("Y") || strProduct_Item_input .equalsIgnoreCase("YES"))
											{
												System.out.print("Enter Product Category: ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												adminClass.Setproduct((br.readLine()).toUpperCase());
												adminClass.SetItemName(null);
											}
											else
											{
												System.out.print("Enter Product Category: ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												adminClass.Setproduct((br.readLine()).toUpperCase());
												
												System.out.print("Enter ItemName: ");	
												br = new BufferedReader(new InputStreamReader(System.in));
												adminClass.SetItemName((br.readLine()).toUpperCase());
											}
											
											System.out.println();

											//Calling AdminRole Command Invoker to call server Updateitems function
											adminCommandInvoker.execute("Remove");
											
											break;
								case "4": 
											// For browsing an Item
											System.out.println("Enter Product Category (Main Menu: 'MEN' or 'Women'): ");
											br = new BufferedReader(new InputStreamReader(System.in));
											product = br.readLine().toUpperCase();
											  
											// Implemented Abstract Factory to get the AdminRoleController object
											browseItemFactory = BrowseItemFactory.BrowseFactoryList(usersession); 
											
											iBrowse = browseItemFactory.BrowseFactory();
											
											// calling AdminRole Controller function
											iBrowse.BrowseItems(product);
											break;
									
								case "0" : 
									System.out.println("Exiting....");
									break;			
								default:
									System.out.println("Invalid Selection....");		
							}		
					} while (!adminInput.equals("0"));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error Occured " + e.getMessage());
				}
		}
	
		public void displayMessages(String message)
		{
			System.out.println(message);
		}

		// Function to display the Product list or Product Category
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
					System.out.println("---------------------------------------------");
					System.out.println("ItemName" + "\t" + "Price" + "\t" + "Quantity");
					System.out.println("---------------------------------------------");
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
}
