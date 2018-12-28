package step3.src.de.imut.oop.talkv3.server.command.set;

import step3.src.de.imut.oop.talkv3.Dispatcher;
import step3.src.de.imut.oop.talkv3.command.Context;

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
        super();
        this.user = user;
        this.message = message;
    }

    /**
     * The overriding execute-method from serverCommand.
     */
    public void execute(Context context) {
        Dispatcher.broadcastMessage(this.user, this.message, context);
    }

}