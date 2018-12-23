package step5.src.de.imut.oop.talkv3;

import step5.src.de.imut.oop.talkv3.command.Context;
import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * CommunicatorClient.java
 * 
 * @author Gruppe 1- PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class CommunicatorClient extends Communicator {

    // the sender
    private SenderClient sender;

    // the incomingQueue
    private ArrayBlockingQueue<RemoteCommand> queueIncoming;

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     */
    public CommunicatorClient(Socket socket) {
        
    	inputCommandProcessor();    // starts the inputCommandProcessor 

        this.sender = new SenderClient(socket, this.queueIncoming);
        Thread senderThread = new Thread(this.sender, "SenderClient");
        senderThread.start();

        this.receiver = new ReceiverClient(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverClient");
        receiverThread.start();

        Context context = null;
        remoteCommandProcessor(context);  // starts the remoteCommandProcessor
    }

    /**
     * Method to start the inputCommandProcessor for the incomingQueue
     */
    private void inputCommandProcessor() {
        int capacity = 10;
        this.queueIncoming = new ArrayBlockingQueue<RemoteCommand>(capacity);
        InputCommandProcessor processor = new InputCommandProcessor(this.queueIncoming);
        Thread processorThread = new Thread(processor, "InputProcessorThread");
        processorThread.start();
    }

    /**
     * Returns the senderClient.
     * 
     * @return sender - the SenderClient
     */
    public SenderClient getSender() {
        return sender;
    }
}
