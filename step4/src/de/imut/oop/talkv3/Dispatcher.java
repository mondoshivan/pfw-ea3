package step4.src.de.imut.oop.talkv3;

import step4.src.de.imut.oop.talkv3.client.command.set.MessageCommand;
import step4.src.de.imut.oop.talkv3.client.command.set.SetContextCommand;
import step4.src.de.imut.oop.talkv3.command.Context;
import step4.src.de.imut.oop.talkv3.common.SystemExitCode;

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
public class Dispatcher implements Runnable {

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
     * The method to broadcast the userName, the message to the clients.
     * 
     * @param user - the userName of the user
     * @param message - the message from the user
     * @param context - the context (id) of the sending client
     */
    public static void broadcastMessage(String user, String message, Context context) {
        List<Communicator> communicators = CommunicatorFactory.getInstance().getCommunicators();
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

    /**
     * The method to remove the clients from the server after "exit."-command
     * @param userName - the userName of the user
     * @param context - the context of the client (id)
     */
    public static void removeClient(String userName, Context context) {
        System.out.println("Exit Command received from id: " + context.getId());
        List<Communicator> communicators = CommunicatorFactory.getInstance().getCommunicators();
        for (Communicator communicator : communicators) {
            CommunicatorServer communicatorServer = (CommunicatorServer) communicator;
            if (communicatorServer.getContext().getId() == context.getId()) {
                Socket socket = communicatorServer.getSender().getSocket();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CommunicatorFactory.getInstance().removeCommunicator(communicatorServer);
                Dispatcher.broadcastMessage(userName, "exit.", context);
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
                SetContextCommand contextCommand = new SetContextCommand(communicatorServer.getContext());
                communicatorServer.getSender().sendCommand(contextCommand);
                server.close();
            } catch (IOException e) {
                Thread.yield();
            }
        }
    }
}
