package de.imut.oop.talkv3.client.command.set;

/**
* MessageCommand.java
* The class for sending the messages.
* 
* @author Gruppe 1 - PFW WS 2018/19
* @version 1.00, 05.12.2018
*
*/
public class MessageCommand extends ClientCommand {

	//the String variable for the user
	private String user;
	
	// the string variable for the message	
	private String message;
	
    // the serialVersionUID
	private static final long serialVersionUID = 1L;
	
	
    /**
     * The Constructor of the messageCommand.
     * 
     * @param user
     * 			- the user variable.
     * @param message
     * 			- the message variable.
     */
    public MessageCommand(String user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.print(user + ": ");
        System.out.println(message);
    }

}
