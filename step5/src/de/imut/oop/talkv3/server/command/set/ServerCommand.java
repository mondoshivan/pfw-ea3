package de.imut.oop.talkv3.server.command.set;

import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;

/**
 * ServerCommand.java
 * The abstract class for ServerCommands.
 * @author Freak
 *
 */
public interface ServerCommand {

	void execute(Context context);
}
