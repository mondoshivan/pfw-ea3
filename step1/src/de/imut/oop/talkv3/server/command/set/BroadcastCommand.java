package step1.src.de.imut.oop.talkv3.server.command.set;

/**
 * BroadcastCommand.java
 * 
 * The class for broadcasting the command to the clients.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class BroadcastCommand extends ServerCommand {

    /**
	 * the default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	// the variable message
	private String message;
	
	// the variable user
    private String user;

    /**
     * The Constructor of the braodcast.
     * 
     * @param user
     * 			- the user.
     * @param message
     * 			- the message.
     */
    public BroadcastCommand(String user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.print(this.getUser() + ": ");
        System.out.println(this.getMessage());
    }

    /**
     * gets and return the user.
     * 
     * @return user.
     */
    public String getUser() {
        return user;
    }

    
    /**
     * gets and return the message.
     * 
     * @return message.
     */
    public String getMessage() {
        return message;
    }
}