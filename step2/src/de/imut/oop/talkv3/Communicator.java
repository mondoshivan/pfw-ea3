package step2.src.de.imut.oop.talkv3;

/**
 * Communicator.java
 * 
 * The communicator class.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public abstract class Communicator {

	// the sender
    protected Sender sender;
    
    // the receiver
    protected ReceiverClient receiver;

    /**
     * Method to get the sender of the chat
     * @return sender - the sender
     */
    public Sender getSender() {
        return sender;
    }
}
