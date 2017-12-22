package Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.util.ArrayList;
import Interface.*;
import ServerClass.Session;

//Shopping class which extends UnicastRemoteObject and implementing IShopping  interface
public class Shopping extends UnicastRemoteObject implements IShopping 
{	
	public Shopping() throws RemoteException {
		super();
	}
	
	// function to browse product list
	public ArrayList<String> BrowseItems(Session usersession, String Productname)  
	{	

		ArrayList<String> arrProductList = new ArrayList<String>(); 
		PreparedStatement PrepareBrowseStmt1 = null;
		PreparedStatement PrepareBrowseStmt2 = null;
		
		String query1 = null;
		String query2 = null;
		String strProduct = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int parentIDValue = -1;
		int IDValue = -1;
		
		// Creating an object of ExecuteQuery class
		ExecuteQuery exe = new ExecuteQuery(); 
		Connection conn = DatabaseConnection.getconnection();
		
		try { 
			
	  		  System.out.println("---------------------------------------------------------"); 
			  System.out.println("BrowseItems : " + Productname );
			  
			  // Retrieve ID , ParentID From ProductCategory table
			  query1 = " Select ID , ParentID From ProductCategory Where ProductCategoryName = '" + Productname + "'";
			  rs1 =  exe.execute(query1);
		      
			  while(rs1.next())
			  {
				  IDValue = rs1.getInt("ID");
				  parentIDValue = rs1.getInt("ParentID");
			  }
	  
			  if (parentIDValue == 0)
			  {  
				  // Retrieve ProductCategoryName From ProductCategory table and add it into the arrProductList array
				  query2 = "Select ProductCategoryName From ProductCategory Where ParentID = " + IDValue + "";
				  rs2 = exe.execute(query2);
				  while(rs2.next())
				  {
					  arrProductList.add(rs2.getString("ProductCategoryName"));
				  }
			  }
			  else
			  {
				  // Retrieve ProductName, Price, Quantity From ProductList table and add it into the arrProductList array
				  query2 = "Select ProductName, Price, Quantity From ProductList Where ProductCategory = " + IDValue + "";
				  rs2 = exe.execute(query2);
				  while(rs2.next())
				  {
					  strProduct = rs2.getString("ProductName") + "|" + rs2.getString("Price") + "|" + rs2.getString("Quantity");
					  arrProductList.add(strProduct);
				  }
			  }
			 return arrProductList;
	       } 
	       catch (Exception e) {
	    	   System.out.println("Error: " + e.getMessage().toString());
	    	   e.printStackTrace();
	    	   return null;
	       }		
		
	}
	
	 // function to place an order	
	 public synchronized String PlaceOrder(Session usersession, String Product, String itemName, int Quantity, String OrderType) {
		 
		 PreparedStatement PrepareUpdateStmt = null;
		 PreparedStatement PrepareStmt = null;
			
		 String query = null;
		 ResultSet rs = null;
		 int updatedQty = 0;	
		 
	     ExecuteQuery exe = new ExecuteQuery(); 
		 Connection conn = DatabaseConnection.getconnection();
		 
		 try { 
			 
			     System.out.println("---------------------------------------------------------"); 
			     System.out.println("PlaceOrder : " + Product + " ->  item :" + itemName + " -> OrderType: " + Quantity);
			    
				 // Retrieve Quantity From ProductList table
				 query = "Select Quantity From ProductList Where ProductName = '" + itemName + "'";
				 rs = exe.execute(query);

				 // Logic for Purchase OR Return
				 if (rs.next())
				 {
						int actualQuantity = rs.getInt("Quantity");
						
						if((actualQuantity - Quantity) >= 0 && OrderType.equalsIgnoreCase("PURCHASE"))
						{
							updatedQty =  (actualQuantity - Quantity);
						}
						else if ((actualQuantity - Quantity) < 0 && OrderType.equals("PURCHASE"))
						{
							return "Quantity Ordered is more than Stock";
						}
						else if (OrderType.equals("RETURN"))
						{
							updatedQty =  actualQuantity + Quantity;
						}
						
						// Update Statement
						PrepareUpdateStmt =  (PreparedStatement) conn.prepareStatement("Update ProductList Set  Quantity = " + updatedQty +" Where ProductName = '" + itemName + "'");
						int i = PrepareUpdateStmt.executeUpdate();
						
						if (i > 0){

							return "true";
						}
						else
						{
							return "false";
						}
						
				 }
				 else{
						return "false";
				 }
		  } 
		  catch (Exception e) { 
			  System.out.println("Error : " + e.getMessage().toString());
			  e.printStackTrace();
			  return "false";
		  } 
	}
	
	public ArrayList<String> ConcurrencyBrowseItems(Session usersession,String Product) {
		 
		 try {
			 ArrayList<String> arrProductList = BrowseItems(usersession,Product);
			 return arrProductList;
		  } 
		  catch (Exception e) { 
			  System.out.println("Error : " + e.getMessage().toString());
			  e.printStackTrace();
			  return null;
		  } 
	 }
}
