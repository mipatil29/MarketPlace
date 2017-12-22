package Command;
import Controller.AdminRoleController;
import Interface.ICommand;

public class AddCommand implements ICommand{

	AdminRoleController admincontroller;
	
	// assigning object to AdminRoleController
	public AddCommand(AdminRoleController admncontroler){
			this.admincontroller = admncontroler;
	}

	// executing Admin Controller Additems Function
	public void execute() {
			System.out.println("Command -> AddCommand() ");
			admincontroller.AddItems();
	}
}
