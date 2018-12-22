package de.imut.oop.talkv3.client.command.set;

import de.imut.oop.talkv3.command.RemoteCommand;

public class PingResponseCommand implements RemoteCommand, ClientCommand {

    /**
     * the default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private long startTime;
    private long oneWayTime;

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
