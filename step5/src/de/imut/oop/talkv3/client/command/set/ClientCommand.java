package step5.src.de.imut.oop.talkv3.client.command.set;


import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

/**
 * ClientCommand.java
 * 
 * An abstract class for clientCommands.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public interface ClientCommand extends RemoteCommand {

	/**
	 * Executes the stored command.
	 * Concrete implementations of commands have to be provided in sub-classes.
	 */
	void execute();
}
