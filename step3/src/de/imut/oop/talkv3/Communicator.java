package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;
import de.imut.oop.talkv3.server.command.set.ServerCommand;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Communicator.java
 * 
 * The communicator class.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public abstract class Communicator {

	// the sender
    protected Sender sender;

    // the receiver
    protected ReceiverClient receiver;

    protected ArrayBlockingQueue<RemoteCommand> queue;

    protected void createProcessor(Context context) {
        int capacity = 10;
        this.queue = new ArrayBlockingQueue<RemoteCommand>(capacity);
        RemoteCommandProcessor processor = new RemoteCommandProcessor(this.queue, context);
        Thread processorThread = new Thread(processor, "ProcessorThread");
        processorThread.start();
    }

    public ArrayBlockingQueue<RemoteCommand> getInRemoteCommandQueue() {
        return queue;
    }

    public Sender getSender() {
        return sender;
    }

}
