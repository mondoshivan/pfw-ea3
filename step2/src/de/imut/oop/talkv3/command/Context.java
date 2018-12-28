package step2.src.de.imut.oop.talkv3.command;

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

    // the static variable instances to calculate the clientID
    static private int instances = 0;
    
    // the ClientID
    private int id;

    /**
     * The constructor of the class Context.
     */
    public Context() {
        this.id = instances ++;
    }

    /**
     * The method to return the ClientID.
     * 
     * @return id - the clientID
     */
    public int getId() {
        return id;
    }
}
