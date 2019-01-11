package step5.src.de.imut.oop.talkv3.communication;

import step5.src.de.imut.oop.talkv3.command.RemoteCommand;
import step5.src.de.imut.oop.talkv3.common.SystemExitCode;
import step5.src.de.imut.oop.talkv3.server.command.set.ExitCommand;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Sender.java
 * <p>
 * A simple sender of network traffic.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 */
public class Sender implements Runnable {
    // the variable socket for the sender
    private Socket socket;

    // the objectoutputstream
    private ObjectOutputStream outputStream;

    // the queue with the commands of the user
    private ArrayBlockingQueue<RemoteCommand> queue;

    /**
     * The constructor of the Sender class.
     * 
     * @param socket - the socket
     * @param queue - the queue with the commands
     */
    public Sender(Socket socket, ArrayBlockingQueue<RemoteCommand> queue) {
        this.socket = socket;
        this.queue = queue;

        try {
            this.outputStream = new ObjectOutputStream(new DataOutputStream(getSocket().getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to get the command of the user.
     *
     * @return command
     * - the command of the user.
     */
    private RemoteCommand getCommand() throws InterruptedException {
        return this.queue.take();
    }

    /**
     * Sending the command to the receiver (server).
     *
     * @param command - the command to send.
     */
    public void sendCommand(RemoteCommand command) {
        try {
            this.outputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Sender thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        RemoteCommand command = null;

        do {
            try {
                command = getCommand();
                sendCommand(command);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!(command instanceof ExitCommand));


        System.out.println("Communication ended.");
        System.exit(SystemExitCode.NORMAL.returnExitCode());
    }

    /**
     * Returns the socket of the Sender.
     *
     * @return socket - the socket of the senderServer
     */
    public Socket getSocket() {
        return this.socket;
    }
}