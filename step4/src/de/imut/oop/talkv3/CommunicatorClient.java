package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class CommunicatorClient extends Communicator {

    // the sender
    private SenderClient sender;

    private ArrayBlockingQueue<RemoteCommand> queueIncoming;

    /**
     * The constructor of the Communicator class.
     *
     * @param socket
     * 			- the socket
     */
    public CommunicatorClient(Socket socket) {
        inputCommandProcessor();

        this.sender = new SenderClient(socket, this.queueIncoming);
        Thread senderThread = new Thread(this.sender, "SenderClient");
        senderThread.start();

        this.receiver = new ReceiverClient(socket, this);
        Thread receiverThread = new Thread(this.receiver, "ReceiverClient");
        receiverThread.start();

        Context context = null;
        remoteCommandProcessor(context);
    }

    private void inputCommandProcessor() {
        int capacity = 10;
        this.queueIncoming = new ArrayBlockingQueue<RemoteCommand>(capacity);
        InputCommandProcessor processor = new InputCommandProcessor(this.queueIncoming);
        Thread processorThread = new Thread(processor, "InputProcessorThread");
        processorThread.start();
    }

    public SenderClient getSender() {
        return sender;
    }
}
