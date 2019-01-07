package step5.src.de.imut.oop.talkv3.server.command.set;

import step5.src.de.imut.oop.talkv3.command.Context;
import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

/**
 * ServerCommand.java
 * 
 * The abstract class for ServerCommands.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public interface ServerCommand extends RemoteCommand {

	void execute(Context context);
}
