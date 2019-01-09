package step5.src.de.imut.oop.talkv3;

import step5.src.de.imut.oop.talkv3.command.RemoteCommand;
import step5.src.de.imut.oop.talkv3.common.SystemExitCode;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * ReceiverServer.java
 * The class of the Server of the Receiver.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class ReceiverServer extends ReceiverClient {

	// the variable for the instance of the communicator
    private CommunicatorServer communicator;

    /**
     * The constructor of the ReceiverServer.
     * 
     * @param socket - the socket of the receiverServer
     * @param communicator - the communicator instance
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
        
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());

            do
            {
                try
                {
                    RemoteCommand remoteCommand = (RemoteCommand) in.readObject();
                    communicator.getQueueOutgoing().add(remoteCommand);
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
