package step5.src.de.imut.oop.talkv3.client.command.set;

import step5.src.de.imut.oop.talkv3.command.RemoteCommand;

/**
 * PingResponseCommand.java
 * 
 * The class for calculating and printing the ping response time to the client console.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class PingResponseCommand implements RemoteCommand, ClientCommand {

    /**
     * the default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    // the startTime of 
    private long startTime;
    
    // the oneWay time from client to server, if reachable
    private long oneWayTime;
    
    /**
     * The constructor of the class.
     * 
     * @param startTime - the startTime of the pingCommand of the user
     * @param oneWayTime - the time value from sending to server reached
     */
    public PingResponseCommand(long startTime, long oneWayTime) {
        super();
        this.startTime = startTime;
        this.oneWayTime = oneWayTime;
    }

    @Override
    public void execute() {
        long backAtClientTime = System.nanoTime();

        // calculating the round trip and server time
        long roundTripTime = backAtClientTime - startTime;
        long serverTime = this.oneWayTime - startTime;

        System.out.println("Received ping reply, time to server: " + serverTime + " ns, roundtrip: " + roundTripTime + " ns");
    }
}
