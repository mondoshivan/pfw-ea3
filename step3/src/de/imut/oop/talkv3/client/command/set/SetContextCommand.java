package step3.src.de.imut.oop.talkv3.client.command.set;

import step3.src.de.imut.oop.talkv3.TalkClient;
import step3.src.de.imut.oop.talkv3.command.Context;

/**
 * SetContextCommand.java
 * 
 * The class for printing the client id-Number on screen.
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
