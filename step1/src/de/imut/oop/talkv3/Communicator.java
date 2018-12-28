package step1.src.de.imut.oop.talkv3;


/**
 * Communicator.java
 * 
 * The communicator class.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 22.12.2018
 *
 */
public abstract class Communicator {

	// the sender
    protected Sender sender;
    
    // the receiver
    protected ReceiverClient receiver;

    /**
     * The constructor of the communicator
     * 
     * @return 
     * 			- the sender of the chat
     */
    public Sender getSender() {
        return sender;
    }
}
