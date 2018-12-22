package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.RemoteCommand;
import de.imut.oop.talkv3.common.SystemExitCode;
import de.imut.oop.talkv3.server.command.set.BroadcastCommand;
import de.imut.oop.talkv3.server.command.set.ExitCommand;

import java.io.*;
import java.net.Socket;

/**
 * Sender.java
 * <p>
 * A simple sender of network traffic.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 */
public class Sender implements Runnable {
    // the variable socket for the sender
    private Socket socket;

    // the objectoutputstream
    private ObjectOutputStream outputStream;

    // the bufferedReader
    private BufferedReader inputReader;

    // the name of the user
    private String userName;


    public Socket getSocket() {
        return this.socket;
    }

    /**
     * A sender of information over the network.
     *
     * @param socket - the socket of the sender
     */
    public Sender(Socket socket) {
        this.socket = socket;
        inputReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            outputStream = new ObjectOutputStream(new DataOutputStream(getSocket().getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ObjectOutputStream getObjectOutputStream() {
        return outputStream;
    }

    /**
     * The method to get the command of the user.
     *
     * @return command
     * - the command of the user.
     */
    private RemoteCommand getCommand() {
        String line = null;
        try {
            line = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RemoteCommand command;
        if (line.equals("exit.")) {
            command = new ExitCommand(userName);
        } else {
            command = new BroadcastCommand(userName, line);
        }
        return command;
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
        RemoteCommand command;

        do {
            command = getCommand();
            sendCommand(command);
        } while (!(command instanceof ExitCommand));


        System.out.println("Communication ended.");
        System.exit(SystemExitCode.NORMAL.returnExitCode());
    }


    /**
     * Setting the name of the user.
     *
     * @param userName - the userName variable.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}