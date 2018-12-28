package step2.src.de.imut.oop.talkv3.command;

import java.io.Serializable;

/**
 * RemoteCommand.java
 * The Super-interface RemoteCommand.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public interface RemoteCommand extends Serializable {

    /**
     * Executes the stored command.
     * Concrete implementations of commands have to be provided in sub-classes.
     */
    public void execute();

}
