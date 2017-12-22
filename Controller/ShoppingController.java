package Controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import Interface.IBrowseItems;
import Interface.IShopping;
import View.ShoppingView;
import ServerClass.Session;

//ShoppingController implementing IBrowseItems interface
public class ShoppingController implements IBrowseItems{

	private ShoppingView shopngVw;
	IShopping ishopng;
	Session usersession=  null;
	
	//This constructor is used to initialize ShoppingView,IShopping
	public ShoppingController(ShoppingView shoppingVw,IShopping ishopping)
	{
		try {
				this.shopngVw = shoppingVw;
				this.ishopng = ishopping;
		} 
		catch (Exception e) {
			shoppingVw.displayMessages("Error : " + e.getMessage());
		}
	}

	//This constructor is used to initialize ShoppingView,IShopping,Session
	public ShoppingController(ShoppingView shoppingVw,IShopping ishopping,Session usersession)
	{
		try {
				this.usersession = usersession;
				this.shopngVw = shoppingVw;
				this.ishopng = ishopping;
		} 
		catch (Exception e) {
			shoppingVw.displayMessages("Error : " + e.getMessage());
		}
	}
	
	
	// Browse Product list function
	public void BrowseItems(String productname) {
		ArrayList<String> lstProduct = new ArrayList<String>();
		shopngVw.displayMessages("ShoppingController -> BrowseItems() ");
		
		try { 
			  
			  // Calling Server Browse Items using RMI
			  lstProduct = ishopng.BrowseItems(usersession,productname);
			  if(lstProduct != null){
				  shopngVw.displayProductList(lstProduct);		
			  }
			  else{
				  shopngVw.displayMessages("No Item Found..!");
			  }
		} 
		catch (Exception e) {
			shopngVw.displayMessages("Error : " + e.getMessage());
		}
	}
	
	
	// Placing an Order function
	public void PlaceOrder(String strProduct,String strItemName,int quantity,String strOrderType,Session usersession) {
	
	  String strResponse = null;
	  try 
	   {
		  	shopngVw.displayMessages("ShoppingController -> PlaceOrder() ");
		  	
		  	// Calling Server Place Order function using RMI
		  	System.out.println(usersession + ", " + strProduct + "," + strItemName + "," + quantity + "," + strOrderType);
		  	
		  	strResponse = ishopng.PlaceOrder(usersession,strProduct,strItemName,quantity,strOrderType);
		  	
			if(strResponse.equalsIgnoreCase("true"))
			{
				if (strOrderType.equalsIgnoreCase("PURCHASE"))
				{
					shopngVw.displayMessages("Product added to your Cart and will Deliver Successfully");	
				}
				else if (strOrderType.equalsIgnoreCase("RETURN"))
				{
					shopngVw.displayMessages("Product Removed from your List Successfully");	
				}
			}
			else{
				shopngVw.displayMessages("Ordered Product is not in a List");	
			}
		}
		catch(Exception e)
		{
			shopngVw.displayMessages("Error :" + e.getMessage());
		}
	}
	
	public void ConcurrencyBrowseItems(Session usersession) {
		
		ArrayList<String> arrayProduct = new ArrayList<String>();
		String p1 ="MEN";
		String p2 ="WOMEN";
		String p3 ="SHOES";
		String p4 ="PURSE";
		String p5 ="JEANS";
		String p6 ="T-SHIRTS";
		
		arrayProduct.add(p1);
		arrayProduct.add(p2);
		arrayProduct.add(p3);
		arrayProduct.add(p4);
		arrayProduct.add(p5);
		arrayProduct.add(p6);
		
		try {
				Thread t1 = new Thread(new Runnable() {
				 public void run() {

					    	for(int i=0;i<6;i++)
							{
					    	  try {
						    		 Thread.sleep(5000);
							    	 ArrayList<String> lstProduct = new ArrayList<String>();
									 lstProduct = ishopng.ConcurrencyBrowseItems(usersession,arrayProduct.get(i));
									 if(lstProduct != null){
										 	shopngVw.displayMessages("Process: " + i);
										 	shopngVw.displayMessages(arrayProduct.get(i));
										 	shopngVw.displayProductList(lstProduct);
											shopngVw.displayMessages("---------------------------");
									 }
								} 
								catch (Exception e) {
										shopngVw.displayMessages("Error : " + e.getMessage());
									}
						         }
					    	}
					});  
					t1.start();
			} 
			catch (Exception e) {
				shopngVw.displayMessages("Error : " + e.getMessage());
			}
	}
	
}
