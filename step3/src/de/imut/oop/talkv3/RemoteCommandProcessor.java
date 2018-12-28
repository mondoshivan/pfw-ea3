package step3.src.de.imut.oop.talkv3;

import step3.src.de.imut.oop.talkv3.client.command.set.ClientCommand;
import step3.src.de.imut.oop.talkv3.command.Context;
import step3.src.de.imut.oop.talkv3.command.RemoteCommand;
import step3.src.de.imut.oop.talkv3.server.command.set.ServerCommand;

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

	// the queue of the commands of the user
    private ArrayBlockingQueue<RemoteCommand> queue;
    
    // the client context (id)
    private Context context;

    
    /**
     * The constructor of the class.
     * 
     * @param queue - the queue of the commands
     * @param context - the client id of the client
     */
    public RemoteCommandProcessor(ArrayBlockingQueue<RemoteCommand> queue, Context context) {
        this.queue = queue;
        this.context = context;
    }


    /**
     * The implementation of the standard run-method of the runnable interface
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
