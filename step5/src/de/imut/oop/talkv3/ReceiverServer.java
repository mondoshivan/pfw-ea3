package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.RemoteCommand;
import de.imut.oop.talkv3.common.SystemExitCode;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ReceiverServer extends ReceiverClient {

    private CommunicatorServer communicator;

    /**
     * The constructor of the ReceiverServer.
     *
     * @param socket
     * 			- the socket
     */
    public ReceiverServer(Socket socket, CommunicatorServer communicator)
    {
        super(socket, communicator);
        this.communicator = communicator;
    }

    /*
     * ReceiverClient thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        RemoteCommand command;
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());

            do
            {
                try
                {
                    RemoteCommand remoteCommand = (RemoteCommand) in.readObject();
                    communicator.getInRemoteCommandQueue().add(remoteCommand);
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
