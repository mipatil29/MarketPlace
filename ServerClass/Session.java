package ServerClass;
import java.io.Serializable;

public class Session implements Serializable {
	private static final long serialVersionUID = -6745473220581903527L;
	
	private User user;
	
	public Session(String userType) {
		user = new User(userType);
	}
		
	// get method to return user
	public User getUser() {
		return user;
	}

}