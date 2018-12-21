package de.imut.oop.talkv3.command;

import java.io.Serializable;

public class Context implements Serializable {

    /**
     * the standard serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    static private int instances = 0;
    private int id;

    public Context() {
        this.id = instances ++;
    }

    public int getId() {
        return id;
    }
}
