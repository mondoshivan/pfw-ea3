package step2.src.de.imut.oop.talkv3.client.command.set;

import step2.src.de.imut.oop.talkv3.TalkClient;
import step2.src.de.imut.oop.talkv3.command.Context;

/**
 * SetContextCommand.java
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class SetContextCommand extends ClientCommand {

    /**
     * the standard serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    // the instance of the class Context
    private Context context;

    /**
     * The constructor of the class.
     * 
     * @param context
     * 			- the context of the client.
     */
    public SetContextCommand(Context context) {
        this.context = context;
    }

    @Override
    public void execute() {
        TalkClient.context = context;
        System.out.println("Client ID set to " + context.getId());
    }
}
