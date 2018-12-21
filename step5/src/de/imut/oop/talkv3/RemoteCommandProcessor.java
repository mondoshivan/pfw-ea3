package de.imut.oop.talkv3;

import de.imut.oop.talkv3.client.command.set.ClientCommand;
import de.imut.oop.talkv3.command.Context;
import de.imut.oop.talkv3.command.RemoteCommand;
import de.imut.oop.talkv3.server.command.set.ServerCommand;

import java.util.concurrent.ArrayBlockingQueue;

public class RemoteCommandProcessor implements Runnable{

    private ArrayBlockingQueue<RemoteCommand> queue;
    private Context context;

    public RemoteCommandProcessor(ArrayBlockingQueue<RemoteCommand> queue, Context context) {
        this.queue = queue;
        this.context = context;
    }


    @Override
    public void run() {
        while(true) {
            try {
                if (this.queue.size() != 0) {
                    RemoteCommand command = queue.take();
                    if (command instanceof ClientCommand) {
                        ClientCommand clientCommand = (ClientCommand) command;
                        clientCommand.execute();
                    } else if (command instanceof ServerCommand) {
                        ServerCommand serverCommand = (ServerCommand) command;
                        serverCommand.execute(context);
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
