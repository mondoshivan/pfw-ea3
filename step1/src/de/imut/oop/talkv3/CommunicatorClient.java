package de.imut.oop.talkv3;

import java.net.Socket;

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

        this.receiver = new ReceiverClient(socket);
        Thread receiverThread = new Thread(this.receiver, "ReceiverClient");
        receiverThread.start();
    }
}
