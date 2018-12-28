package step4.src.de.imut.oop.talkv3;

import step4.src.de.imut.oop.talkv3.command.Context;

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

    // the variable for the instance of the SenderServer    
    private SenderServer sender;

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     * @param context
     * 			- the context of the client
     */
    public CommunicatorServer(Socket socket, Context context) {
        this.sender = new SenderServer(socket);

        this.receiver = new ReceiverServer(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverServer");
        receiverThread.start();

        this.context = context;

        remoteCommandProcessor(getContext());
    }
    
    /**
     * The method to get the context of the client.
     * 
     * @return context
     * 				- the context of the client
     */

    public Context getContext() {
        return context;
    }

    /**
     * The method to return the SenderServer.
     * 
     * @return sender
     * 			- the senderServer
     */
    public SenderServer getSender() {
        return sender;
    }
}
