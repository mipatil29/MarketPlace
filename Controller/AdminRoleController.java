package Controller;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Interface.IAdminRole;
import Interface.IBrowseItems;
import View.AdminView;
import ServerClass.Session;

//AdminRoleController implementing IBrowseItems interface
public class AdminRoleController implements IBrowseItems{

	AdminView adminVw;
	IAdminRole iadmin;
	AdminProperties adminClass;
	Session usersession = null;	
	
	//This constructor is used to initialize AdminView,IAdminRole,AdminProperties,Session 
	public AdminRoleController(AdminView admnVw,IAdminRole iadmn,AdminProperties admnCls,Session usersession)
	{
		try {
				this.adminVw = admnVw;
				this.iadmin = iadmn;
				this.adminClass = admnCls;
				this.usersession = usersession;
		} 
		catch (Exception e) {
			e.printStackTrace();
			adminVw.displayMessages("Error : " + e.getMessage());
		}
	}
	
	//This constructor is used to initialize AdminView,IAdminRole,Session
	public AdminRoleController(AdminView admnVw,IAdminRole iadmn,Session usersession){ 
		try {
				this.adminVw = admnVw;
				this.iadmin = iadmn;
				this.usersession = usersession;				
			} 
			catch (Exception e) {
				adminVw.displayMessages("Error : " + e.getMessage());
			}
	}
	
	// AdminRoleController AddItems 
	public void AddItems()
	{
		String parentPrductCategory;
		String product;
		String productdesc;
		String strResponse = null;
		adminVw.displayMessages("AdminRoleController -> AddItems()");
		
		try {
			
			//Calling get methods
			parentPrductCategory =adminClass.getParentPrductCategory();
			product = adminClass.getProduct();
			productdesc= adminClass.getProductDesc();
			
			//Calling Server AddItems function
			strResponse = iadmin.AddItems(usersession,parentPrductCategory, product, productdesc);
		} 
		catch (Exception e) {
			strResponse = e.getMessage();
		}

		adminVw.displayMessages(strResponse);
	}
	
	// AdminRoleController UpdateItems  using RMI
	public void UpdateItems()
	{
		String product;
		String itemName; 
		String value;
		String UpdateType;
		String strResponse = null;
		
		adminVw.displayMessages("AdminRoleController -> UpdateItems()");
		
		try {
			
			//Calling get methods
			product = adminClass.getProduct();
			itemName = adminClass.getItemName();
			value = adminClass.getProductDesc();
			UpdateType = adminClass.getUpdateType();
			
			//Calling Server updateItems function
			strResponse = iadmin.UpdateItems(usersession,product, itemName, value, UpdateType);
		} 
		catch (Exception e) {
			strResponse = e.getMessage();
		}

		adminVw.displayMessages(strResponse);
		
	}
	
	// AdminRoleController RemoveItems  using RMI
	public void RemoveItems()
	{
		String Product;
		String itemName;
		String strResponse = null;
		
		adminVw.displayMessages("AdminRoleController -> RemoveItems()");
		
		try {
			
			//Calling get methods
			Product = adminClass.getProduct();
			itemName =adminClass.getItemName();
			
			//Calling Server RemoveItems function
			strResponse = iadmin.RemoveItems(usersession,Product, itemName);
		} 
		catch (Exception e) {
			strResponse = e.getMessage();
		}

		adminVw.displayMessages(strResponse);
	}
	
	
	// AdminRoleController BrowseItems using RMI
	public void BrowseItems(String Productname) {
		
		adminVw.displayMessages("AdminRoleController -> BrowseItems()");
		
		//  creating object of ExecutorService
		ExecutorService executor = Executors.newFixedThreadPool(10);

		// Future Pattern
		Future<?> future = executor.submit(new Callable<Void>() {
			
			public Void call() throws Exception {
				
				ArrayList<String> lstProduct = new ArrayList<String>();
				try { 
					  //Calling Server BrowseItems function
					  lstProduct = iadmin.BrowseItems(usersession,Productname);
					  if(lstProduct != null){
						  //calling display method on View (To Display the Product List)
						  adminVw.displayProductList(lstProduct);		
					  }
					  else{
						  adminVw.displayMessages("No Item Found..!");
					  }
				} 
				catch (Exception e) {
					adminVw.displayMessages("Error : " + e.getMessage());
				}
				
				return null;
			}
		}); 
		executor.shutdown();
		try {
			future.get();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
