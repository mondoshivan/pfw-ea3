package step5.src.de.imut.oop.talkv3.server.command.set;

import step5.src.de.imut.oop.talkv3.Dispatcher;
import step5.src.de.imut.oop.talkv3.command.Context;
import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

/**
 * BroadcastCommand.java
 * 
 * The class for broadcasting the command to the clients.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class BroadcastCommand implements RemoteCommand, ServerCommand {

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
     * The overrided method execute from the ServerCommand interface.
     */
    @Override
    public void execute(Context context) {
        Dispatcher.broadcastMessage(this.user, this.message, context);
    }

}