package step4.src.de.imut.oop.talkv3.server.command.set;

import step4.src.de.imut.oop.talkv3.Dispatcher;
import step4.src.de.imut.oop.talkv3.command.Context;

/**
 * ExitCommand.java
 *
 * The class for the regular exit of the program.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class ExitCommand extends ServerCommand {

	// default serialVersionUID
	private static final long serialVersionUID = 1L;
	
	// the userName 
	private String userName;

	/**
	 * The constructor of the Exitcommand.
	 *
	 * @param code
	 * 			- the code of the exit-Command.
	 */
	public ExitCommand(String userName) {
		super();
		this.userName = userName;
		
	}
	
	/**
	 * The implementation of the execute-method from the superInterface - RemoteCommand.
	 */
	@Override
	public void execute(Context context) {
		Dispatcher.removeClient(userName, context);
	}
}