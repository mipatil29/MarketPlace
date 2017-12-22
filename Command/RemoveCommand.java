package Command;
import Controller.AdminRoleController;
import Interface.ICommand;

public class RemoveCommand implements ICommand{

	AdminRoleController admincontroller;
	
	// assigning object to AdminRoleController
	public RemoveCommand(AdminRoleController admncontroler){
			this.admincontroller = admncontroler;
	}
	
	// executing Admin Controller Remove Function
	public void execute() {
		System.out.println("Command -> RemoveCommand() ");	
		admincontroller.RemoveItems();
	}

	
}
