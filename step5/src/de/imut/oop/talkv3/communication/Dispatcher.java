package step5.src.de.imut.oop.talkv3.communication;

import step5.src.de.imut.oop.talkv3.client.command.set.MessageCommand;
import step5.src.de.imut.oop.talkv3.client.command.set.PingResponseCommand;
import step5.src.de.imut.oop.talkv3.client.command.set.SetContextCommand;
import step5.src.de.imut.oop.talkv3.command.Context;
import step5.src.de.imut.oop.talkv3.common.SystemExitCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

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

    // the list of the communicator
    private static ConcurrentHashMap<Context, CommunicatorServer> communicators = new ConcurrentHashMap<Context, CommunicatorServer>();

    /**
     * The constructor of the dispatcher.
     * 
     * @param port 
     * 			- the port of the server
     */
    public Dispatcher(int port) {
        this.listeningPort = port;
    }

    /**
     * Removes the communicator instance from the server.
     *
     * @param context
     * 			- the context
     */
    private static void removeCommunicator(Context context) {
        Dispatcher.communicators.remove(context);
    }

    /**
     * The method to broadcast the userName, the message to the clients.
     * 
     * @param user - the userName of the user
     * @param message - the message from the user
     * @param context - the context (id) of the sending client
     */
    public static void broadcastMessage(String user, String message, Context context) {
        System.out.println("Message \"[" + user + "] " + message + "\" received!");
        MessageCommand messageCommand = new MessageCommand(user, message);

        for (CommunicatorServer communicator : Dispatcher.communicators.values()) {
            int id = communicator.getContext().getId();
            if (id == context.getId()) continue;
            System.out.println(" -> redirecting to client " + id);
            communicator.getQueueIncoming().add(messageCommand);
        }
    }
    
    
    /**
     * The method to remove the clients from the server after "exit."-command
     * @param userName - the userName of the user
     * @param context - the context of the client (id)
     */
    public static void removeClient(String userName, Context context) {
        System.out.println("Exit Command received from id: " + context.getId());
        CommunicatorServer communicator = Dispatcher.communicators.get(context);
        communicator.close();
        Dispatcher.removeCommunicator(context);
        Dispatcher.broadcastMessage(userName, "exit.", context);

        if (communicators.size() == 0) {
            System.out.println("No more clients available - shutting down server");
            System.exit(SystemExitCode.NORMAL.returnExitCode());
        }
    }

    /**
     * The method to send the ping reply message to the client.
     * 
     * @param startTime - the startTime of sending the command
     * @param context - the client context of the sending sender
     */
    public static void pingResponse(long startTime, Context context) {
        System.out.println("Received ping request from client : " + context.getId());
        CommunicatorServer communicator = Dispatcher.communicators.get(context);
        PingResponseCommand command = new PingResponseCommand(startTime, System.nanoTime());
        communicator.getQueueIncoming().add(command);
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
                CommunicatorServer communicator = (CommunicatorServer) CommunicatorFactory.getInstance().createCommunicator(socket, isServerCommunicator);
                Context context = communicator.getContext();
                Dispatcher.communicators.put(context, communicator);
                SetContextCommand contextCommand = new SetContextCommand(context);
                communicator.getQueueIncoming().add(contextCommand);
                server.close();
            } catch (IOException e) {
                Thread.yield();
            }
        }
    }
}
