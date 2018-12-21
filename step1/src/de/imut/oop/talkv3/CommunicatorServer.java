package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.Context;

import java.net.Socket;

public class CommunicatorServer extends Communicator {

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
     * returns the receiver.
     * @return
     * 			- the receiver
     */
    public ReceiverServer getReceiver() {
        return (ReceiverServer) receiver;
    }

}
