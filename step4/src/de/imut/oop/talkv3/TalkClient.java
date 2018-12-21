package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.common.Common;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TalkClient.java
 * 
 * The Client of the chat.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public class TalkClient {

	// the default server port
    private static final int DEFAULT_SERVER_PORT = 2048;
    
    // the default server ip
    private static final String DEFAULT_SERVER_IP = "localhost";

    public static Context context;

    public static String username;

    /**
     * GetSocket - method.
     * 
     * @param serverIP 
     * 			- the serverIP.
     * @param serverPort
     * 			- the serverPort.
     * 
     * @return socket
     * 			- the socket.
     */
    private Socket getSocket(String serverIP, int serverPort) {
        System.out.println("Trying to connect to remote " + serverIP + ":" + serverPort);
        
        Socket socket = null;
        do {
            try {
                socket = new Socket(serverIP, serverPort);
            }catch (UnknownHostException e) {
                System.err.println(e);
                System.exit(-1);
            } catch (IOException e) {
                Thread.yield();
            }
        } while (socket == null);

        System.out.println("End communication with line = \"exit.\"");
        System.out.println("Connection established to remote" + socket.getInetAddress() + ":" + socket.getPort() +
    			" from local adress" + socket.getInetAddress()+":"+socket.getLocalPort());
        return socket;
    }

    /**
	 * The main method.
	 * 
	 * @param args 
	 * 			  arguments transferred from the operating system args[0]: the
     *            port to listen to (default: 2048) args[1]: the port to talk to
     *            (default: 2049) args[2]: remote machine to talk to (default:
     *            localhost)
	 * 
	 */
    public static void main(final String[] args) {
        String serverIP = DEFAULT_SERVER_IP;
        int serverPort = DEFAULT_SERVER_PORT;
        TalkClient.username = Common.getUserName();

        switch (args.length)
        {
            case 2:
                serverIP = args[1];
            case 1:
                serverPort = Common.parsePort(args[0], DEFAULT_SERVER_PORT);
        }

        TalkClient client = new TalkClient();
        Socket socket = client.getSocket(serverIP, serverPort);
        boolean isServerCommunicator = false;
        CommunicatorFactory factory = CommunicatorFactory.getInstance();
        Communicator communicator = factory.createCommunicator(socket, isServerCommunicator);
    }

}
