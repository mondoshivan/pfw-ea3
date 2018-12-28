package step1.src.de.imut.oop.talkv3;

import step1.src.de.imut.oop.talkv3.command.CommandListener;
import step1.src.de.imut.oop.talkv3.command.RemoteCommand;
import step1.src.de.imut.oop.talkv3.common.SystemExitCode;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * ReceiverServer.java
 * The class of the Server of the Receiver.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class ReceiverServer extends ReceiverClient {

	// the instance of the communicatorServer
    private CommunicatorServer communicator;

    // the list of commandListeners
    private List<CommandListener> listeners;

    /**
     * The constructor of the ReceiverServer.
     *
     * @param socket
     * 			- the socket
     */
    public ReceiverServer(Socket socket, CommunicatorServer communicator)
    {
        super(socket);
        this.communicator = communicator;
        this.listeners = new ArrayList<CommandListener>();
    }

    /**
     * Add-method for the listener to the list.
     *
     * @param listener
     * 			- the listener.
     */
    public void addListener(CommandListener listener) {
        listeners.add(listener);
    }

    /**
     * the method to call the broadcast command.
     * @param command
     * 			- broadcast or exitCommand
     */
    private void notifyListeners(RemoteCommand command) {
        for (CommandListener listener : listeners) {
            listener.call(
                    command,
                    communicator.getContext()
            );
        }
    }

    /*
     * ReceiverClient thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());

            do
            {
                try
                {
                    RemoteCommand remoteCommand = (RemoteCommand) in.readObject();
                    notifyListeners(remoteCommand);
                }
                catch (EOFException e)	// e.g., remote connection closed connection (without sending and ExitCommand)
                {
                    // do nothing
                }
                catch (SocketException e)
                {
                    // no nothing (2)
                }
            }
            while (true);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Remote connection closed ... exiting.");
        System.exit(SystemExitCode.ABORT.returnExitCode());
    }
}
