package de.imut.oop.talkv3;

import de.imut.oop.talkv3.client.command.set.ClientCommand;
import de.imut.oop.talkv3.command.RemoteCommand;
import de.imut.oop.talkv3.common.SystemExitCode;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ReceiverClient.java
 *
 * A simple receiver of network traffic.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 */
public class ReceiverClient implements Runnable
{
    // the socket variable
    protected Socket socket;

    private Communicator communicator;

    /**
     * The constructor of the ReceiverClient.
     *
     * @param socket
     * 			- the socket
     */
    public ReceiverClient(Socket socket, Communicator communicator)
    {
        this.communicator = communicator;
        this.socket = socket;
    }

    /*
     * ReceiverClient thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        RemoteCommand command;
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());

            do
            {
                command = (RemoteCommand) in.readObject();
                communicator.getInRemoteCommandQueue().add(command);

            }
            while (true);
        }
        catch (EOFException ef)	// remote connection closed connection (without sending and ExitCommand)
        {
            // do nothing
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Remote connection closed ... exiting.");
        System.exit(SystemExitCode.ABORT.returnExitCode());
    }
}