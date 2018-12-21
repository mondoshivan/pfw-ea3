package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;

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

    // the receiver
    protected ReceiverClient receiver;

    protected ArrayBlockingQueue<RemoteCommand> queueOutgoing;

    protected void remoteCommandProcessor(Context context) {
        int capacity = 10;
        this.queueOutgoing = new ArrayBlockingQueue<RemoteCommand>(capacity);
        RemoteCommandProcessor processor = new RemoteCommandProcessor(this.queueOutgoing, context);
        Thread processorThread = new Thread(processor, "OutputProcessorThread");
        processorThread.start();
    }

    public ArrayBlockingQueue<RemoteCommand> getInRemoteCommandQueue() {
        return queueOutgoing;
    }

}
