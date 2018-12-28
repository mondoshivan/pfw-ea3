package step4.src.de.imut.oop.talkv3.command;

import java.io.Serializable;

/**
 * Context.java
 * The class for calculating the id´s of the client.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01 - 22.12.2018
 *
 */
public class Context implements Serializable {

    /**
     * the standard serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    // the integer-variable to count the id of the client
    static private int instances = 0;
    
    // the client ID
    private int id;

    /**
     * The constructor to calculate the clientID
     */
    public Context() {
        this.id = instances ++;
    }

    /**
     * Method to return the clientID.
     * 
     * @return id - the client ID
     */
    public int getId() {
        return id;
    }
}
