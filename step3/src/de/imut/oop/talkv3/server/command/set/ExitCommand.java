package de.imut.oop.talkv3.server.command.set;
import de.imut.oop.talkv3.Dispatcher;
import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.common.SystemExitCode;

/**
 * ExitCommand.java
 *
 * The class for the regular exit of the program.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public class ExitCommand extends ServerCommand {

	// default serialVersionUID
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor of the Exitcommand.
	 *
	 * @param code
	 * 			- the code of the exit-Command.
	 */
	public ExitCommand() {
		super();
	}
	
	@Override
	public void execute(Context context) {
		Dispatcher.removeClient(context);
	}
}