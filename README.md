Makefile will compile all the java files and generate respective class files in the same folder. These files are require to run the application successfully. Policy file is require for the client and server communication using java rmi.

1. You can compile all the files on tesla server and then run the applications on different machines (as they are clone machine).

	a. Open a new console tesla server
	b. cd %Filepath% (Filepath:- ~/Code/MarketPlace/)
	c. make 

2. Login to machine in-csci-rrpc01.cs.iupui.edu (10.234.136.55- This is a server in my case)

    To complie and run Registry:

	a. Open a new console 
	b. cd %Filepath% (Filepath:- ~/Code/MarketPlace/)
	c. rmiregistry 1990 &

    To run Server:
	a. Open a new console on the same machine (in-csci-rrpc01.cs.iupui.edu-10.234.136.55)
	b. cd %FilePath% (Filepath:- ~/Code/MarketPlace/)
	c. java -cp ".:./mysql-connector-java-5.1.41-bin.jar" -Djava.security.policy=policy ServerClass.MarketPlaceServer


3. Login to machine:
 
• in-csci-rrpc02.cs.iupui.edu - 10.234.136.56
• in-csci-rrpc03.cs.iupui.edu - 10.234.136.57
• in-csci-rrpc04.cs.iupui.edu - 10.234.136.58
• in-csci-rrpc05.cs.iupui.edu - 10.234.136.59
• in-csci-rrpc06.cs.iupui.edu - 10.234.136.60

(These machines are clients in my case)

   To run Client:
	a. Open a new console to run client (Use different machines for different client log in)
	b. cd %FilePath%  (Filepath:- ~/Code/MarketPlace/)
	c. java -Djava.security.policy=policy View.Client
	d. User name and Passwords are in Point Number 3.


4. Refer Sample Run (screen shot) to check/run functionality.

5. User name and passwords are store in UserDataList.xml file.
   Admin Login :- Username- ADMIN, Password- ADMIN
   Customer Login :- Username- MILAN, Password- MILAN
		     Username- MADHU, Password- MADHU