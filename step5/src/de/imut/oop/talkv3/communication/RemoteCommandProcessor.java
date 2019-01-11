package step5.src.de.imut.oop.talkv3.communication;

import step5.src.de.imut.oop.talkv3.command.Context;
import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

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

	// the queue of the remoteCommands
	private ArrayBlockingQueue<RemoteCommand> queue;
    
	// the context fo the clientIDs
	private Context context;

	/**
	 * The constructor of the RemoteCommandProcessor.
	 * 
	 * @param queue - the queue
	 * @param context - the context
	 */
    public RemoteCommandProcessor(ArrayBlockingQueue<RemoteCommand> queue, Context context) {
        this.queue = queue;
        this.context = context;
    }


    @Override
    public void run() {
        while(true) {
            try {
                RemoteCommand command = queue.take();
                command.execute(context);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
