package de.imut.oop.talkv3.server.command.set;

import de.imut.oop.talkv3.Dispatcher;
import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;

/**
 * ExitCommand.java
 *
 * The class for the regular exit of the program.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public class ExitCommand implements RemoteCommand, ServerCommand {

	// default serialVersionUID
	private static final long serialVersionUID = 1L;

	private String username;

	/**
	 * The constructor of the Exitcommand.
	 *
	 * @param code
	 * 			- the code of the exit-Command.
	 */
	public ExitCommand(String username) {
		super();
		this.username = username;
	}
	
	@Override
	public void execute(Context context) {
		Dispatcher.removeClient(username, context);
	}
}