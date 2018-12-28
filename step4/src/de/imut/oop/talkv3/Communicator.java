package step4.src.de.imut.oop.talkv3; 

import step4.src.de.imut.oop.talkv3.command.Context;
import step4.src.de.imut.oop.talkv3.command.RemoteCommand;

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

    // the outgoingQueue of the sender
    protected ArrayBlockingQueue<RemoteCommand> queueOutgoing;

    
    /**
     * The protected method remoteCommandProcessor with starting the Thread of the processor.
     * 
     * @param context
     * 			- the context of the client (ID)
     */
    protected void remoteCommandProcessor(Context context) {
        int capacity = 10;
        this.queueOutgoing = new ArrayBlockingQueue<RemoteCommand>(capacity);
        RemoteCommandProcessor processor = new RemoteCommandProcessor(this.queueOutgoing, context);
        Thread processorThread = new Thread(processor, "OutputProcessorThread");
        processorThread.start();
    }

    /**
     * The method to return the outgoingQueue.
     * 
     * @return queueOutgoing
     * 			- the outgoing Queue with the commands of the user
     */
    public ArrayBlockingQueue<RemoteCommand> getInRemoteCommandQueue() {
        return queueOutgoing;
    }

}
