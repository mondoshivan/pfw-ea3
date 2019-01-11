package step5.src.de.imut.oop.talkv3.communication;

import step5.src.de.imut.oop.talkv3.TalkClient;
import step5.src.de.imut.oop.talkv3.command.RemoteCommand;
import step5.src.de.imut.oop.talkv3.server.command.set.BroadcastCommand;
import step5.src.de.imut.oop.talkv3.server.command.set.ExitCommand;
import step5.src.de.imut.oop.talkv3.server.command.set.PingRequestCommand;

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

	// the queue of incoming commands
    private ArrayBlockingQueue<RemoteCommand> queue;

    // the bufferedReader
    private BufferedReader inputReader;

    /**
     * The constructor of the class.
     * 
     * @param queue - the queue with the incomingQueue commands
     */
    public InputCommandProcessor(ArrayBlockingQueue<RemoteCommand> queue) {
        this.queue = queue;
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads a line from STDIN
     *
     * @return string
     * - the received line.
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
        } else if (line.equals("ping.")) {
            command = new PingRequestCommand();
        } else {
            command = new BroadcastCommand(TalkClient.userName, line);
        }
        return command;
    }

    @Override
    public void run() {
        RemoteCommand command;

        do {
            command = getCommand();
            this.queue.add(command);
        } while (!(command instanceof ExitCommand));
    }
}
