package step3.src.de.imut.oop.talkv3.command;

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

    // the variable to calculate the client id´s
    static private int instances = 0;
    
    // the clientID´s
    private int id;

    /**
     * The constructor of the class.
     */
    public Context() {
        this.id = instances ++;
    }

    /**
     * The method to get the id.
     * 
     * @return id - the client id
     */
    public int getId() {
        return id;
    }
}
