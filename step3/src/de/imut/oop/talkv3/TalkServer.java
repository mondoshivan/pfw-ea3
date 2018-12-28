package step3.src.de.imut.oop.talkv3;

import step3.src.de.imut.oop.talkv3.common.Common;

/**
 * TalkServer.java
 * 
 * The server of the Chat.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class TalkServer {

	// the default listening port
    private static final int DEFAULT_LISTEN = 2048;

    /**
     * the method to create the dispatcher.
     * 
     * @param port
     * 			- the port of the dispatcher.
     */
    private void createDispatcher(int port) {
        Dispatcher dispatcher = new Dispatcher(port);
        Thread dispatcherThread = new Thread(dispatcher, "Dispatcher");
        dispatcherThread.start();
    }

    /**
     * The main method.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        int port = DEFAULT_LISTEN;
        if (args.length == 1)
            port = Common.parsePort(args[0], DEFAULT_LISTEN);

        System.out.println("Server started. Listening for incoming connection requests on port: " + port);
        TalkServer server = new TalkServer();
        server.createDispatcher(port);
    }
}
