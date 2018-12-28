package step4.src.de.imut.oop.talkv3;

import step4.src.de.imut.oop.talkv3.command.Context;
import step4.src.de.imut.oop.talkv3.command.RemoteCommand;

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

    // the incomingQueue with the commands of the user
    private ArrayBlockingQueue<RemoteCommand> queueIncoming;

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     */
    public CommunicatorClient(Socket socket) {
        inputCommandProcessor();

        this.sender = new SenderClient(socket, this.queueIncoming);
        Thread senderThread = new Thread(this.sender, "SenderClient");
        senderThread.start();

        this.receiver = new ReceiverClient(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverClient");
        receiverThread.start();

        Context context = null;
        remoteCommandProcessor(context);
    }

    /**
     * Method to create the Thread of the clientCommunicator.
     */
    private void inputCommandProcessor() {
        int capacity = 10;
        this.queueIncoming = new ArrayBlockingQueue<RemoteCommand>(capacity);
        InputCommandProcessor processor = new InputCommandProcessor(this.queueIncoming);
        Thread processorThread = new Thread(processor, "InputProcessorThread");
        processorThread.start();
    }

    /**
     * The method to get the SenderClient.
     * 
     * @return sender
     * 			- the senderClient
     */
    public SenderClient getSender() {
        return sender;
    }
}
