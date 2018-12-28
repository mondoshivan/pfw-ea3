package step1.src.de.imut.oop.talkv3;

import step1.src.de.imut.oop.talkv3.command.Context;

import java.net.Socket;

/**
 * CommunicatorServer.java
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01 - 22.12.2018
 *
 */
public class CommunicatorServer extends Communicator {

	// the variable context
    private Context context;

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     */
    public CommunicatorServer(Socket socket, Context context) {
        this.sender = new Sender(socket);
        Thread senderThread = new Thread(this.sender, "Sender");
        senderThread.start();

        this.receiver = new ReceiverServer(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverServer");
        receiverThread.start();

        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    /**
     * returns the object of the ReceiverServer.
     * @return
     * 			- the receiver
     */
    public ReceiverServer getReceiver() {
        return (ReceiverServer) receiver;
    }

}
