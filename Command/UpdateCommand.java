package Command;
import Controller.AdminRoleController;
import Interface.ICommand;

public class UpdateCommand implements ICommand{

	AdminRoleController admincontroller;
	
	// assigning object to AdminRoleController
	public UpdateCommand(AdminRoleController admncontroler){
			this.admincontroller = admncontroler;
	}
	
	// executing Admin Controller Update Function
	public void execute() {
		 	System.out.println("Command -> UpdateCommand() ");	
			admincontroller.UpdateItems();
	}

}
