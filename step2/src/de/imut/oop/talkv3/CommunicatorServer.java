package step2.src.de.imut.oop.talkv3;

import step2.src.de.imut.oop.talkv3.command.Context;

import java.net.Socket;

/**
 * CommunicatorServer.java
 * 
 * @author Gruppe 1 -PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class CommunicatorServer extends Communicator {

	// the variable for the instance of the context
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

    /**
     * The method to get the Context.
     * 
     * @return context - the context of the server.
     */
    public Context getContext() {
        return context;
    }

    /**
     * returns the receiver.
     * @return
     * 			- the receiver
     */
    public ReceiverServer getReceiver() {
        return (ReceiverServer) receiver;
    }

}
