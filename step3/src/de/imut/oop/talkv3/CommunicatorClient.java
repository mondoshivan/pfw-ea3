package step3.src.de.imut.oop.talkv3;

import step3.src.de.imut.oop.talkv3.command.Context;

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
        this.sender = new Sender(socket);
        Thread senderThread = new Thread(this.sender, "Sender");
        senderThread.start();

        this.receiver = new ReceiverClient(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverClient");
        receiverThread.start();

        Context context = null;
        createProcessor(context);
    }
}
