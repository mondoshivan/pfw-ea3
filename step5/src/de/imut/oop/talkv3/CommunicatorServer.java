package step5.src.de.imut.oop.talkv3;

import step5.src.de.imut.oop.talkv3.command.Context;

import java.net.Socket;

/**
 * CommunicatorServer.java
 * 
 * @author Gruppe 1 -PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class CommunicatorServer extends Communicator {

	// the private Instance of the context variable
    private Context context;
    
    // the variable for the instance of the SenderServer 
    private SenderServer sender;

   /**
    * The constructor of the CommunicatorServer.
    * 
    * @param socket - the socket
    * @param context - the context
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
     * The method to get the context.
     * 
     * @return context - the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * The method to return the sender.
     * 
     * @return sender - the sender
     */
    public SenderServer getSender() {
        return sender;
    }
}
