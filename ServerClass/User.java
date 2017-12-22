package ServerClass;
import java.io.Serializable;

public class User implements Serializable {	
	private static final long serialVersionUID = 8084523177681775893L;
	
	private String roleType;
	
	// creating User role based on user(ADMIN or CUSTOMER)
	public User(String roleType) {
		
		if(roleType.equalsIgnoreCase("ADMIN"))
		{
			this.roleType = "ADMIN";
		}
		else
		{
			this.roleType = "CUSTOMER";	
		}
	}
	
	// get method to return user role type
	public String getRoleType() {
		return roleType;
	}
	
}