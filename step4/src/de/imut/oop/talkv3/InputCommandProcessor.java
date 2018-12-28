package step4.src.de.imut.oop.talkv3;

import step4.src.de.imut.oop.talkv3.command.RemoteCommand;
import step4.src.de.imut.oop.talkv3.server.command.set.BroadcastCommand;
import step4.src.de.imut.oop.talkv3.server.command.set.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * InputCommandProcessor.javva
 * The ProcessorClass for executing the incoming commands.
 *  
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class InputCommandProcessor implements Runnable{

	// the queue of the command
    private ArrayBlockingQueue<RemoteCommand> queue;

    // the bufferedReader
    private BufferedReader inputReader;

    /**
     * The constructor of the class InputCommandProcessor.
     * 
     * @param queue - the queue with the userCommands
     */
    public InputCommandProcessor(ArrayBlockingQueue<RemoteCommand> queue) {
        this.queue = queue;
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * The method to read the inputLine of the user.
     * 
     * @return line - the message of the user
     */
    private String readLine() {
        String line = null;
        try {
            line = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * The method to get the command of the user.
     *
     * @return command
     * - the command of the user.
     */
    private RemoteCommand getCommand() {
        String line = readLine();

        RemoteCommand command;
        if (line.equals("exit.")) {
            command = new ExitCommand(TalkClient.userName);
        } else {
            command = new BroadcastCommand(TalkClient.userName, line);
        }
        return command;
    }

    /**
     * The overriding standard method run of the interface Runnable. 
     */
    @Override
    public void run() {
        RemoteCommand command;

        do {
            command = getCommand();
            this.queue.add(command);
        } while (!(command instanceof ExitCommand));
    }
}
