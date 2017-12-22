package Model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecuteQuery {
	
	ResultSet rs = null;
	
	// creating a result set for requested query
	public ResultSet execute(String query)
	{
		try {
				// Create a connection Object and execute a query
				Connection conn = DatabaseConnection.getconnection();
				PreparedStatement PrepareStmt = null;
				PrepareStmt = (PreparedStatement) conn.prepareStatement(query);
				rs = PrepareStmt.executeQuery();
				
				return rs;

			} catch (SQLException e) {
					e.printStackTrace();
					return null;
			}
		
	}
	
}