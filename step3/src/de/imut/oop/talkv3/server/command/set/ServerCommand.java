package de.imut.oop.talkv3.server.command.set;

import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;

/**
 * ServerCommand.java
 * The abstract class for ServerCommands.
 * @author Freak
 *
 */
public abstract class ServerCommand implements RemoteCommand {

	/**
	 * The default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	public abstract void execute(Context context);
}
