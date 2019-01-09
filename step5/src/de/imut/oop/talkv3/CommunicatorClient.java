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
