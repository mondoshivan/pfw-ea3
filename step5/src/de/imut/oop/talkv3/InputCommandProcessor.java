package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.RemoteCommand;
import de.imut.oop.talkv3.server.command.set.BroadcastCommand;
import de.imut.oop.talkv3.server.command.set.ExitCommand;
import de.imut.oop.talkv3.server.command.set.PingRequestCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;

public class InputCommandProcessor implements Runnable{

    private ArrayBlockingQueue<RemoteCommand> queue;

    // the bufferedReader
    private BufferedReader inputReader;

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
            command = new ExitCommand();
        } else if (line.equals("ping.")) {
            command = new PingRequestCommand();
        } else {
            command = new BroadcastCommand(TalkClient.username, line);
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
