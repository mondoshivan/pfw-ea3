package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.Context;

import java.net.Socket;

public class CommunicatorServer extends Communicator {

    private Context context;

    private SenderServer sender;

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     */
    public CommunicatorServer(Socket socket, Context context) {
        this.sender = new SenderServer(socket);

        this.receiver = new ReceiverServer(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverServer");
        receiverThread.start();

        this.context = context;

        remoteCommandProcessor(getContext());
    }

    public Context getContext() {
        return context;
    }

    public SenderServer getSender() {
        return sender;
    }
}
