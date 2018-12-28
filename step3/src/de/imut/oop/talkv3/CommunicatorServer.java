package step3.src.de.imut.oop.talkv3;

import step3.src.de.imut.oop.talkv3.command.Context;

import java.net.Socket;

/**
 * CommunicatorServer.java
 * 
 * @author Gruppe 1 -PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class CommunicatorServer extends Communicator {

	// the instance variable of context
    private Context context;

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     * @param context
     * 			- the context of the client
     */
    public CommunicatorServer(Socket socket, Context context) {
        this.sender = new Sender(socket);
        Thread senderThread = new Thread(this.sender, "Sender");
        senderThread.start();

        this.receiver = new ReceiverServer(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverServer");
        receiverThread.start();

        this.context = context;

        createProcessor(getContext());
    }

    /**
     * The method to get get context.
     * 
     * @return context
     * 			- the context of the client.
     */
    public Context getContext() {
        return context;
    }
}
