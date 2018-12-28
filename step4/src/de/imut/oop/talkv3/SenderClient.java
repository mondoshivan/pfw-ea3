package step4.src.de.imut.oop.talkv3;

import step4.src.de.imut.oop.talkv3.command.RemoteCommand;
import step4.src.de.imut.oop.talkv3.common.SystemExitCode;
import step4.src.de.imut.oop.talkv3.server.command.set.ExitCommand;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * SenderClient.java
 * <p>
 * A simple sender of network traffic.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 */
public class SenderClient implements Runnable {
    // the variable socket for the sender
    private Socket socket;

    // the objectoutputstream
    private ObjectOutputStream outputStream;

    // the queue with the commands
    private ArrayBlockingQueue<RemoteCommand> queue;

    /**
     * The constructor of the senderClient class.
     * 
     * @param socket - the socket
     * @param queue - the queue with the commands
     */
    public SenderClient(Socket socket, ArrayBlockingQueue<RemoteCommand> queue) {
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
     * SenderClient thread activation
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

    public Socket getSocket() {
        return this.socket;
    }
}