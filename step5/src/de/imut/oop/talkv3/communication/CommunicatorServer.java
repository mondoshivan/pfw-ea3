package step5.src.de.imut.oop.talkv3.communication;

import step5.src.de.imut.oop.talkv3.command.Context;

import java.io.IOException;
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

   /**
    * The constructor of the CommunicatorServer.
    * 
    * @param socket - the socket
    * @param context - the context
    */
    public CommunicatorServer(Socket socket, Context context) {
        inputCommandProcessor();    // starts the inputCommandProcessor

        this.sender = new Sender(socket, getQueueIncoming());
        Thread senderThread = new Thread(this.sender, "Sender");
        senderThread.start();

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
     * Closes the Sender Socket.
     */
    public void close() {
        Socket socket = this.sender.getSocket();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
