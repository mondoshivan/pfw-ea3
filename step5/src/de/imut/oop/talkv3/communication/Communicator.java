package step5.src.de.imut.oop.talkv3.communication;

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

    // the variable for the instance of the Sender
    protected Sender sender;

    // the receiver
    protected ReceiverClient receiver;

    // the outgoingQueue 
    protected ArrayBlockingQueue<RemoteCommand> queueOutgoing;

    // the incomingQueue
    protected ArrayBlockingQueue<RemoteCommand> queueIncoming;

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
     * Method to start the inputCommandProcessor for the incomingQueue
     */
    protected void inputCommandProcessor() {
        int capacity = 10;
        this.queueIncoming = new ArrayBlockingQueue<RemoteCommand>(capacity);
        InputCommandProcessor processor = new InputCommandProcessor(this.queueIncoming);
        Thread processorThread = new Thread(processor, "InputProcessorThread");
        processorThread.start();
    }

    /**
     * The method to return the queue.
     * 
     * @return queueOutgoing - the outgoingQueue 
     */
    public ArrayBlockingQueue<RemoteCommand> getQueueOutgoing() {
        return queueOutgoing;
    }

    /**
     * The method to return the queue.
     *
     * @return queueIncoming - the incomingQueue
     */
    public ArrayBlockingQueue<RemoteCommand> getQueueIncoming() {
        return queueIncoming;
    }

}
