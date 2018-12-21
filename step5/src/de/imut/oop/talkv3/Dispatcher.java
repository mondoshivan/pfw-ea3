package de.imut.oop.talkv3;

import de.imut.oop.talkv3.client.command.set.MessageCommand;
import de.imut.oop.talkv3.client.command.set.PingResponseCommand;
import de.imut.oop.talkv3.client.command.set.SetContextCommand;
import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.common.SystemExitCode;

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
 * @version 1.00, 05.12.2018
 *
 */
public class Dispatcher implements Runnable {

	// the listeningPort
    private int listeningPort;

    /**
     * The constructor of the dispatcher.
     * 
     * @param port 
     * 			- the port of the server
     */
    public Dispatcher(int port) {
        this.listeningPort = port;
    }

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

    public static void removeClient(Context context) {
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
                break;
            }
        }
        if (communicators.size() == 0) {
            System.out.println("No more clients available - shutting down server");
            System.exit(SystemExitCode.NORMAL.returnExitCode());
        }
    }

    public static void pingResponse(long startTime, Context context) {
        System.out.println("Received ping request from client : " + context.getId());
        List<Communicator> communicators = CommunicatorFactory.getInstance().getCommunicators();
        for (Communicator communicator : communicators) {
            CommunicatorServer communicatorServer = (CommunicatorServer) communicator;
            if (communicatorServer.getContext().getId() == context.getId()) {
                PingResponseCommand command = new PingResponseCommand(startTime, System.nanoTime());
                communicatorServer.getSender().sendCommand(command);
            }
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
                Communicator communicator = CommunicatorFactory.getInstance().createCommunicator(socket, isServerCommunicator);
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
