package step4.src.de.imut.oop.talkv3;

import step4.src.de.imut.oop.talkv3.command.RemoteCommand;

import java.io.*;
import java.net.Socket;

/**
 * SenderServer.java
 * <p>
 * A simple sender of network traffic.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 */
public class SenderServer {
    // the variable socket for the sender
    private Socket socket;

    // the objectoutputstream
    private ObjectOutputStream outputStream;

    /**
     * A sender of information over the network.
     *
     * @param socket - the socket of the sender
     */
    public SenderServer(Socket socket) {
        this.socket = socket;

        try {
            this.outputStream = new ObjectOutputStream(new DataOutputStream(getSocket().getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Socket getSocket() {
        return this.socket;
    }
}