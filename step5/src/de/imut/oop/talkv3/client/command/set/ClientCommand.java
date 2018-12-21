package de.imut.oop.talkv3.client.command.set;

import de.imut.oop.talkv3.command.RemoteCommand;

/**
 * ClientCommand.java
 * 
 * An abstract class for clientCommands.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public interface ClientCommand {

	/**
	 * Executes the stored command.
	 * Concrete implementations of commands have to be provided in sub-classes.
	 */
	void execute();
}
