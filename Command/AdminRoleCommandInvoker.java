package Command;
import java.util.HashMap;

import Controller.AdminRoleController;
import Interface.ICommand;

//Ryan: Always be sure to include useful comments in each file.
public class AdminRoleCommandInvoker {

	//Hash map is used to store the admin command and their respective controller(which will execute at later time)
	private HashMap<String, ICommand> commandMapping = new HashMap<String, ICommand>();
	
	// Constructor to initialize the Admin command
	public AdminRoleCommandInvoker(AdminRoleController adminRoleController) {
		
		commandMapping.put("Add", new AddCommand(adminRoleController));
		commandMapping.put("Update", new UpdateCommand(adminRoleController));
		commandMapping.put("Remove" , new RemoveCommand(adminRoleController));

	}

	//Execute function will receive a command name in string for the execution
	public void execute(String command){
		System.out.println("Command -> AdminRoleCommandInvoker() -> "+ command);	
		commandMapping.get(command).execute();
	}

}
