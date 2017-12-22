package Model;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DatabaseConnection {
	
	public static Connection getconnection() {
		
		// Credentials for Database Connection
		String hostname = "localhost:3306";
		String dbName = "mipatil_db";
				
		String url = "jdbc:mysql://" + hostname + "/" + dbName;
		String username = "mipatil";
		String password = "mipatil";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			// creating connection object
			conn = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
				
			return conn;
		} 
		catch (Exception e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		
	}
	
}
