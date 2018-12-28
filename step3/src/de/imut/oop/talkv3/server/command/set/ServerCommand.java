package step3.src.de.imut.oop.talkv3.server.command.set;

import step3.src.de.imut.oop.talkv3.command.Context;
import step3.src.de.imut.oop.talkv3.command.RemoteCommand;

/**
 * ServerCommand.java
 * The abstract class for ServerCommands.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public abstract class ServerCommand implements RemoteCommand {

	/**
	 * The default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * The abstract method execute.
	 * 
	 * @param context - the context (id) of the client
	 */
	public abstract void execute(Context context);
}
