package de.imut.oop.talkv3.command;

/**
 * CommandListener.java
 * The class for listening the commands.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public interface CommandListener {

	/**
	 * The method to call the command.
	 * 
	 * @param command
	 * 			- the command to broadcast.
	 */
    public void call(RemoteCommand command, Context context);
}
