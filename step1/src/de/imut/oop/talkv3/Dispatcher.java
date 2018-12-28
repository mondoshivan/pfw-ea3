package step1.src.de.imut.oop.talkv3;

import step1.src.de.imut.oop.talkv3.client.command.set.MessageCommand;
import step1.src.de.imut.oop.talkv3.command.CommandListener;
import step1.src.de.imut.oop.talkv3.command.Context;
import step1.src.de.imut.oop.talkv3.command.RemoteCommand;
import step1.src.de.imut.oop.talkv3.common.SystemExitCode;
import step1.src.de.imut.oop.talkv3.server.command.set.BroadcastCommand;
import step1.src.de.imut.oop.talkv3.server.command.set.ExitCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Dispatcher.java
 * 
 * The dispatcher set the connection between client and server.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class Dispatcher implements Runnable, CommandListener {

	// the listeningPort
    private int listeningPort;

    // the factory
    private CommunicatorFactory factory;

    /**
     * The constructor of the dispatcher.
     * 
     * @param port 
     * 			- the port of the server
     */
    public Dispatcher(int port) {
        this.listeningPort = port;
        this.factory = CommunicatorFactory.getInstance();
    }

    /**
     * The method broadcast for broadcasting the message to all recipients (clients) and writing 
     * the receiving message to server.
     * @param broadcastCommand
     */
    public void broadcast(BroadcastCommand broadcastCommand, Context context) {
        List<Communicator> communicators = factory.getCommunicators();
        String user = broadcastCommand.getUser();
        String message = broadcastCommand.getMessage();
        System.out.println("Message \"[" + user + "] " + message + "\" received:");
           
        MessageCommand messageCommand = new MessageCommand(user, message);

        for (Communicator communicator : communicators) {
            CommunicatorServer communicatorServer = (CommunicatorServer) communicator;
            int id = communicatorServer.getContext().getId();
            if (id == context.getId()) continue;
            System.out.println(" -> redirecting to client " + id);
            communicatorServer.getSender().sendCommand(messageCommand);
        }
    }

    public void exit(ExitCommand command, Context context) {
        System.out.println("Exit Command received from id: " + context.getId());
        List<Communicator> communicators = factory.getCommunicators();
        for (Communicator communicator : communicators) {
            CommunicatorServer communicatorServer = (CommunicatorServer) communicator;
            if (communicatorServer.getContext().getId() == context.getId()) {
                Socket socket = communicatorServer.getSender().getSocket();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                factory.removeCommunicator(communicator);
                break;
            }
        }
        if (communicators.size() == 0) {
            System.out.println("No more clients available - shutting down server");
            System.exit(SystemExitCode.NORMAL.returnExitCode());
        }
    }

    /**
     * The implementation of the standard run-method of runnable.
     */
    @Override
    public void run() {
        while (true) {
            try {
                ServerSocket server = new ServerSocket(this.listeningPort);
                Socket socket = server.accept();
                String clientIP = socket.getInetAddress().toString();
                int clientPort = socket.getPort();
                System.out.println("Connection request from " + clientIP + ":" + clientPort);
                System.out.println("Trying to connect to remote " + clientIP + ":" + clientPort);
                System.out.println("Connection established to remote " + clientIP + ":" + clientPort + 
                		" from local adress " + socket.getLocalAddress() + ":" + socket.getLocalPort());
                boolean isServerCommunicator = true;
                Communicator communicator = factory.createCommunicator(socket, isServerCommunicator);
                CommunicatorServer communicatorServer = (CommunicatorServer) communicator;
                communicatorServer.getReceiver().addListener(this);
                server.close();
            } catch (IOException e) {
                Thread.yield();
            }
        }
    }

    /**
     * The implementation of the call-method from the interface commandListener.
     */
    @Override
    public void call(RemoteCommand command, Context context) {
        if (command instanceof BroadcastCommand) broadcast((BroadcastCommand) command, context);
        if (command instanceof ExitCommand) exit((ExitCommand) command, context);
    }
}
