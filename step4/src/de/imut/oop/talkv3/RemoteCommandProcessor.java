package step4.src.de.imut.oop.talkv3;

import step4.src.de.imut.oop.talkv3.client.command.set.ClientCommand;
import step4.src.de.imut.oop.talkv3.command.Context;
import step4.src.de.imut.oop.talkv3.command.RemoteCommand;
import step4.src.de.imut.oop.talkv3.server.command.set.ServerCommand;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * RemoteCommandProcessor.java
 * 
 * The class for handling the commands, executing the commands.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 * 
 */
public class RemoteCommandProcessor implements Runnable{

	// the queue of the commands
    private ArrayBlockingQueue<RemoteCommand> queue;
    
    // the context instance
    private Context context;

    /**
     * The constructor of the class.
     * 
     * @param queue - the queue with the commands of the user
     * @param context - the context of the client
     */
    public RemoteCommandProcessor(ArrayBlockingQueue<RemoteCommand> queue, Context context) {
        this.queue = queue;
        this.context = context;
    }

    
    /**
     * The standard method run from the interface Runnable.
     */
    @Override
    public void run() {
        while(true) {
            try {
                if (this.queue.size() != 0) {
                    RemoteCommand command = queue.take();
                    if (command instanceof ServerCommand) {
                        ServerCommand serverCommand = (ServerCommand) command;
                        serverCommand.execute(context);
                    } else if (command instanceof ClientCommand) {
                        ClientCommand clientCommand = (ClientCommand) command;
                        clientCommand.execute();
                    }
                } else {
                    Thread.yield();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
