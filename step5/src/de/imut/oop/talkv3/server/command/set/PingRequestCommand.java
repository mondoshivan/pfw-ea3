package step5.src.de.imut.oop.talkv3.server.command.set;

import step5.src.de.imut.oop.talkv3.Dispatcher;
import step5.src.de.imut.oop.talkv3.command.Context;
import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

/**
 * PingRequestCommand.java
 * 
 * The class for the request of the pingCommand.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class PingRequestCommand implements RemoteCommand, ServerCommand {

    /**
     * the default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    // the startTime of sending the request
    private long startTime;

    /**
     * The constructor of PingRequestCommand with getting the startTime in nano-Time.
     */
    public PingRequestCommand() {
        super();
        this.startTime = System.nanoTime();
    }

    @Override
    public void execute(Context context) {
        Dispatcher.pingResponse(this.startTime, context);
    }
}
