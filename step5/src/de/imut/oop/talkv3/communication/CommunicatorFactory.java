package step5.src.de.imut.oop.talkv3.communication;

import step5.src.de.imut.oop.talkv3.command.Context;

import java.net.Socket;

/**
 * CommunicatorFactory.java
 * 
 * A Singleton to provide the instance of the factory and create the communicator.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class CommunicatorFactory {

    // holds the singleton instance
    private static final CommunicatorFactory instance = new CommunicatorFactory();

    /**
     * The method to create the CommunicatorFactory
     */
    private CommunicatorFactory() {}

    // provides the singleton instance
    public static CommunicatorFactory getInstance() {
        return CommunicatorFactory.instance;
    }

    /**
     * The method to create the clientCommunicator.
     * 
     * @param socket
     * 			- the socket of the Client
     * @return
     * 			- the Communicator c
     */
    private Communicator createClientCommunicator(Socket socket) {
        return new CommunicatorClient(socket);
    }

    /**
     * The method to create the serverCommunicator.
     * 
     * @param socket
     * 			- the socket of the server
     * @return c
     * 			- the communicator c of the server
     * 
     */
    private Communicator createServerCommunicator(Socket socket) {
        return new CommunicatorServer(socket, new Context());
    }

    /**
     * The method to create the communicator.
     * 
     * @param socket
     * 			- the socket
     * 
     * @return c 
     * 			- the communicator c
     */
    public Communicator createCommunicator(Socket socket, boolean isServerCommunicator) {
        return isServerCommunicator ? createServerCommunicator(socket) : createClientCommunicator(socket);
    }
}
