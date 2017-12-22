JFLAGS = -g
JC = javac -cp ".:./mysql-connector-java-5.1.41-bin.jar"
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
Model/DatabaseConnection.java\
Model/ExecuteQuery.java\
Interface/IAdminRole.java\
Interface/IShopping.java\
Interface/ISignup.java\
Interface/IBrowseItems.java\
Interface/ICommand.java\
Interface/RequiresRole.java\
Model/AdminRole.java\
Model/Shopping.java\
Model/Signup.java\
ServerClass/MarketPlaceServer.java\
ServerClass/AuthorizationException.java\
ServerClass/AuthorizationInvocationHandler.java\
ServerClass/Session.java\
ServerClass/User.java\
Command/AddCommand.java\
Command/AdminRoleCommandInvoker.java\
Command/RemoveCommand.java\
Command/UpdateCommand.java\
AbstractFactory/BrowseItemFactory.java\
AbstractFactory/BrowseItemsAdminFactory.java\
AbstractFactory/BrowseItemsCustomerFactory.java\
Controller/AdminProperties.java\
Controller/AdminRoleController.java\
Controller/Dispatcher.java\
Controller/FrontController.java\
Controller/ShoppingController.java\
Controller/SignupController.java\
View/AdminView.java\
View/Client.java\
View/ShoppingView.java\
View/SignupView.java\

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
