package step3.src.de.imut.oop.talkv3;

import step3.src.de.imut.oop.talkv3.command.Context;
import step3.src.de.imut.oop.talkv3.command.RemoteCommand;

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

	// the sender
    protected Sender sender;

    // the receiver
    protected ReceiverClient receiver;

    // the incomingQueue of the chat for the command
    protected ArrayBlockingQueue<RemoteCommand> queue;

    /**
     * The method to create the Processor for the RemoteCommands.
     * 
     * @param context
     * 			- the context of the clients.
     */
    protected void createProcessor(Context context) {
        int capacity = 10;
        this.queue = new ArrayBlockingQueue<RemoteCommand>(capacity);
        RemoteCommandProcessor processor = new RemoteCommandProcessor(this.queue, context);
        Thread processorThread = new Thread(processor, "ProcessorThread");
        processorThread.start();
    }

    /**
     * The method to get the incomingQueue of the RemoteCommands.
     * 
     * @return queue
     * 			- the queue of the commands
     */
    public ArrayBlockingQueue<RemoteCommand> getInRemoteCommandQueue() {
        return queue;
    }

    /**
     * The method to get the sender.
     * 
     * @return sender - the sender
     * 			
     */
    public Sender getSender() {
        return sender;
    }

}
