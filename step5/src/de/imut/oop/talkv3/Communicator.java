package step5.src.de.imut.oop.talkv3;

import step5.src.de.imut.oop.talkv3.command.Context;
import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Communicator.java
 * 
 * The communicator class.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public abstract class Communicator {

    // the receiver
    protected ReceiverClient receiver;

    // the outgoingQueue 
    protected ArrayBlockingQueue<RemoteCommand> queueOutgoing;

    /**
     * The method to create the Thread of the RemoteCommandProcessor.
     * 
     * @param context - the context (id) of the client
     */
    protected void remoteCommandProcessor(Context context) {
        int capacity = 10;
        this.queueOutgoing = new ArrayBlockingQueue<RemoteCommand>(capacity);
        RemoteCommandProcessor processor = new RemoteCommandProcessor(this.queueOutgoing, context); 
        Thread processorThread = new Thread(processor, "OutputProcessorThread");
        processorThread.start();
    }

    /**
     * The method to return the queue.
     * 
     * @return queueOutgoing - the outgoingQueue 
     */
    public ArrayBlockingQueue<RemoteCommand> getInRemoteCommandQueue() {
        return queueOutgoing;
    }

}
