package ServerClass;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import Interface.RequiresRole;

public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {
	private static final long serialVersionUID = 6925780928377938176L;
	private Object objectImpl;
 
	public AuthorizationInvocationHandler(Object impl) {
	   this.objectImpl = impl;
	}
	 
	// overriding the invoke method
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//check if invoke method requires role 
		if (method.isAnnotationPresent(RequiresRole.class)) {
			
			RequiresRole userRole = method.getAnnotation(RequiresRole.class);
		
			// retriving a Session of a user
			Session session = (Session) args[0];
			
			System.out.println("---------------------------------------------------------"); 
			System.out.println(" User: " + session.getUser().getRoleType());
			System.out.println(" Method Invoke: " + method.getName().toString());
			
			if (session.getUser().getRoleType().equals(userRole.UserRole())) {
				 
				// invoke a method
				return method.invoke(objectImpl, args);
            }
			else
			{
				// if the user is not having authorization then throw a Exception
				throw new AuthorizationException(method.getName());
			}
		}  
		else  {
				// invoke a method 
				return method.invoke(objectImpl, args);
		}   
	}
}