package step5.src.de.imut.oop.talkv3.communication;

import step5.src.de.imut.oop.talkv3.command.Context;

import java.net.Socket;

/**
 * CommunicatorClient.java
 * 
 * @author Gruppe 1- PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class CommunicatorClient extends Communicator {

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     */
    public CommunicatorClient(Socket socket) {
    	inputCommandProcessor();    // starts the inputCommandProcessor 

        this.sender = new Sender(socket, getQueueIncoming());
        Thread senderThread = new Thread(this.sender, "Sender");
        senderThread.start();

        this.receiver = new ReceiverClient(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverClient");
        receiverThread.start();

        Context context = null;
        remoteCommandProcessor(context);  // starts the remoteCommandProcessor
    }

    /**
     * Returns the senderClient.
     * 
     * @return sender - the Sender
     */
    public Sender getSender() {
        return sender;
    }
}
