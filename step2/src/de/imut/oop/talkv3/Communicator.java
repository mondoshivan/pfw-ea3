package de.imut.oop.talkv3;

import de.imut.oop.talkv3.command.RemoteCommand;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Communicator.java
 * 
 * The communicator class.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public abstract class Communicator {

	// the sender
    protected Sender sender;
    
    // the receiver
    protected ReceiverClient receiver;

    public Sender getSender() {
        return sender;
    }
}
