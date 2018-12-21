package de.imut.oop.talkv3.client.command.set;

import de.imut.oop.talkv3.TalkClient;
import de.imut.oop.talkv3.command.Context;

public class SetContextCommand extends ClientCommand {

    /**
     * the standard serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private Context context;

    public SetContextCommand(Context context) {
        this.context = context;
    }

    @Override
    public void execute() {
        TalkClient.context = context;
        System.out.println("Client ID set to " + context.getId());
    }
}
