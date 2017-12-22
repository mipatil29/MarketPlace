package Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Interface.IAdminRole;
import ServerClass.Session;


// AdminRole class which extends UnicastRemoteObject and implementing IAdminRole interface
public class AdminRole extends UnicastRemoteObject implements IAdminRole 
{
	private static final long serialVersionUID = -5485952710128132535L;
	
	//Constructor for Admin Role	
	public AdminRole() throws RemoteException {
		super();
	}

	// Function to Add items
	public String AddItems(Session usersession, String parentProductCategory,String product, String value) 
	{
		 PreparedStatement PrepareAddStmt = null;
		 PreparedStatement PrepareSelectStmt = null;
		 
		 String query = null;
		 String querySelect = null;
		 ResultSet rs = null;
		 
		 Connection conn = DatabaseConnection.getconnection();
		 
		 try
		   {
			  System.out.println("---------------------------------------------------------"); 
			  System.out.println("AddItems Function call to add Product: " + product + " Product Desc: " + value);
			
			  if(parentProductCategory!= null && product != null && value == null) 
		      {
		    	   // Adding a new Product Cateogry to a Parent product
		    	   // the mysql insert statement
				   querySelect = "SELECT ID FROM ProductCategory Where ProductCategoryName = '" + parentProductCategory + "'";
				   query = " insert into ProductCategory(ID,ProductCategoryName,ParentID) values (?,?,?)";
				   
				   // Executing the Select query
				   PrepareSelectStmt = (PreparedStatement) conn.prepareStatement(querySelect);
				   rs = PrepareSelectStmt.executeQuery();
				   
				   // Executing the Insert query
				   PrepareAddStmt = (PreparedStatement) conn.prepareStatement(query);
				   PrepareAddStmt.setString (1, null);
				   PrepareAddStmt.setString (2, product);
		    	   
				   if(rs.next())
			       { 
					   PrepareAddStmt.setString (3,rs.getString("ID"));
			       }
				   else
				   {
					   PrepareAddStmt.setString (3, "0");
				   }
				    
			       // execute the preparedstatement
				   PrepareAddStmt.execute();

				   System.out.println("Admin added a new Category :" + product);
		    	   return "Product Category Added Successfully";

		      }
		      else
		      {	
					// Adding a new Product to a Parent product
		    	   // the mysql insert statement		  
				   querySelect = "SELECT ID FROM ProductCategory Where ProductCategoryName = '" + product + "'";
				   query = " insert into ProductList(ID,ProductName,Price,Quantity,ProductCategory) values (?,?,?,?,?)";

				   // Executing the Select query
				   PrepareSelectStmt = (PreparedStatement) conn.prepareStatement(querySelect);
				   rs = PrepareSelectStmt.executeQuery();
				   
				   PrepareAddStmt = (PreparedStatement) conn.prepareStatement(query);
				   String[] strProduct = value.split("\\|", -1);

				   if(rs.next())
			       { 
					   PrepareAddStmt.setString (1, null);
					   PrepareAddStmt.setString (2, strProduct[0]);
					   PrepareAddStmt.setString (3, strProduct[1]);
					   PrepareAddStmt.setString (4, strProduct[2]);
					   PrepareAddStmt.setInt (5,rs.getInt("ID"));

					   // Executing the Insert query
					   PrepareAddStmt.execute();
					   System.out.println("Admin Added new Item: " + value + " for " + product);
					   return "Product Added Successfully";
			       }
				   else
				   {
			    		return "No Product found to add";
				   }
		      }  
		   }
		   catch(Exception e)
		   {
			   System.out.println("Error: " + e.getMessage().toString());
			   e.printStackTrace();
	    	   return "Error : " + e.getMessage(); 
		   }
	}
	 
	// Function to remove items
	public String RemoveItems(Session usersession, String product, String itemName) 
	{	
				
		 PreparedStatement PrepareRemoveStmt1 = null;
		 PreparedStatement PrepareRemoveStmt2 = null;
		 PreparedStatement PrepareSelectStmt = null;
		 
		 String query1 = null;
		 String query2 = null;
		 
		 String querySelect = null;
		 ResultSet rs = null;

		 Connection conn = DatabaseConnection.getconnection();
		
		try {
			 System.out.println("---------------------------------------------------------");
   			 System.out.println("RemoveItems call to remove product: " + product + " for item: " + itemName);
	    		
	    		if(product !=null && (itemName == null||itemName == ""))
	    		{
		    	    // Retriving the ProductCategory ID 
					// the mysql Delete statement to delete ProductCategory 
	    			querySelect = " SELECT ID FROM ProductCategory Where ProductCategoryName = '" + product + "'";
				    query1 = " Delete From ProductCategory Where ProductCategoryName = '" + product + "'";
					   
			        // executing the Select preparedstatement
				    PrepareSelectStmt = (PreparedStatement) conn.prepareStatement(querySelect);
				    rs = PrepareSelectStmt.executeQuery();
				    
			        // executing the Delete preparedstatement
				    PrepareRemoveStmt1 = (PreparedStatement) conn.prepareStatement(query1);
				    int i = PrepareRemoveStmt1.executeUpdate();
				    int j = -1;				    
				    
				    if (rs.next())
				    {
				    	query2 = " Delete From ProductList Where ProductCategory = " + rs.getInt("ID") + "";
						PrepareRemoveStmt2 = (PreparedStatement) conn.prepareStatement(query2);
						j = PrepareRemoveStmt2.executeUpdate();
				    }
				    
				    if (i > 0 || j > 0)
					{
				    	System.out.println("Admin removed product: " + product);
				    	return "Product Removed Successfully";
					}
				    else
				    {
						return "No Product found to remove";
				    }
		    	}
	    		else{
						// the mysql Delete statement to delete Product
				    	query1 = " Delete From ProductList Where ProductName = '" + itemName + "'";
					    PrepareRemoveStmt1 = (PreparedStatement) conn.prepareStatement(query1);
					    int i = PrepareRemoveStmt1.executeUpdate();
					    
					    if (i > 0)
						{
					    	System.out.println("Admin removed product: " + product);
							return "Product Removed Successfully"; 
						}
					    else
					    {
					    	return "No Product found to remove";
					    }
	    		}
		    } 
		    catch (Exception e) {
		    	e.printStackTrace();
		    	return "Error : " + e.getMessage();
		    }
	
	}
	
	// Function to update items	in a productList xml file
	public String UpdateItems(Session usersession, String Product,String itemName, String value, String Type)  
	{
		 PreparedStatement PrepareUpdateStmt = null;
		 String query = null;
		 Connection conn = DatabaseConnection.getconnection();

		 try { 
			    System.out.println("---------------------------------------------------------");
			    System.out.println("UpdateItems call to update product: " + Product + " for item: " + itemName + " Type: -" + value);
			    
			    int valueUpdate = Integer.parseInt(value);
			    
				// the mysql Update statement to update price or Quantity
			    query = " update ProductList ";
				if(Type.equals("PRICE"))
				{
				    query = query + " Set Price = " + valueUpdate  +"";
				}
				else if(Type.equals("QUANTITY"))
				{
				    query = query + " Set Quantity = " + valueUpdate  +"";
				}
			    query = query + " Where ProductName = '" + itemName  +"'";

				

				// executing the update statement
				PrepareUpdateStmt = (PreparedStatement) conn.prepareStatement(query);
				int i = PrepareUpdateStmt.executeUpdate();
				
				if (i > 0)
				{
					System.out.println("Admin updated product: " + itemName);
					return "Product Details Updated Successfully";	
				}
				else{
					return "No Product Found";
				}
		  } 
		  catch (Exception e) { 
			  e.printStackTrace();
			  return "Error : " + e.getMessage();
		  }
	}
	
	// Function to browse a list of Products
	public ArrayList<String> BrowseItems(Session usersession,String Productname) throws RemoteException {
		
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
		
		Connection conn = DatabaseConnection.getconnection();
		
		try { 
	  		  System.out.println("---------------------------------------------------------"); 
			  System.out.println("BrowseItems : " + Productname );
			  
			  query1 = " Select ID , ParentID From ProductCategory Where ProductCategoryName = '" + Productname + "'";
			   
			  // create the mysql Select preparedstatement
			  PrepareBrowseStmt1 = (PreparedStatement) conn.prepareStatement(query1);
			  rs1 = PrepareBrowseStmt1.executeQuery();

			  while(rs1.next())
			  {
				  IDValue = rs1.getInt("ID");
				  parentIDValue = rs1.getInt("ParentID");
			  }
	  
			  if (parentIDValue == 0)
			  {  
				  // Retrieve ProductCategoryName From ProductCategory Table
				  query2 = "Select ProductCategoryName From ProductCategory Where ParentID = " + IDValue + "";
				  PrepareBrowseStmt2 = (PreparedStatement) conn.prepareStatement(query2);
				  rs2 = PrepareBrowseStmt2.executeQuery();
				  while(rs2.next())
				  {
					  arrProductList.add(rs2.getString("ProductCategoryName"));
				  }
			  }
			  else
			  {
				  // Retrieve ProductName, Price, Quantity From ProductList Table
				  query2 = "Select ProductName, Price, Quantity From ProductList Where ProductCategory = " + IDValue + "";
				  PrepareBrowseStmt2 = (PreparedStatement) conn.prepareStatement(query2);
				  rs2 = PrepareBrowseStmt2.executeQuery();
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
}
