package de.imut.oop.talkv3.server.command.set;

import de.imut.oop.talkv3.Dispatcher;
import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;

public class PingRequestCommand implements RemoteCommand, ServerCommand {

    /**
     * the default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private long startTime;

    public PingRequestCommand() {
        this.startTime = System.nanoTime();
    }

    @Override
    public void execute(Context context) {
        Dispatcher.pingResponse(this.startTime, context);
    }
}
