package step2.src.de.imut.oop.talkv3;

import step2.src.de.imut.oop.talkv3.command.Context;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

    private List<Communicator> communicators;

    // private: to ensure that only one instance can be created
    private CommunicatorFactory() {
        this.communicators = new ArrayList<Communicator>();
    }

    // provides the singleton instance
    public static CommunicatorFactory getInstance() {
        return instance;
    }

    /**
     * The method to create the clientCommunicator.
     * 
     * @param socket
     * 			- the socket of the Client
     * @return
     * 			- the Communicator c of the client
     */
    private Communicator createClientCommunicator(Socket socket) {
        Communicator c = new CommunicatorClient(socket);
        this.communicators.add(c);
        return c;
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
        Communicator c = new CommunicatorServer(socket, new Context());
        this.communicators.add(c);
        return c;
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

    /**
     * Removes the communicator instance from the server.
     *
     * @param communicator
     * 			- the communicator
     */
    public void removeCommunicator(Communicator communicator) {
        communicators.remove(communicator);
    }
    
    /**
     * The List of the communicator.
     * 
     * @return communicators - a list of the communicator
     */
    public List<Communicator> getCommunicators() {
        return communicators;
    }
}
