package de.imut.oop.talkv3.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Common.java
 * 
 * The class with the essential methods parsePort and getUserName.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.00, 05.12.2018
 *
 */
public class Common {

    /**
     * Parses the port (string) and returns the port number (int). If the string
     * is not a number the defaultPort ist returned.
     *
     * @param port
     *            (string) to be parsed
     * @param defaultPort
     *            if port string cannot be parsed
     * @return parsed port
     */
    public static int parsePort(String port, int defaultPort) {
        try
        {
            return Integer.parseInt(port);
        }
        catch (NumberFormatException e)
        {
            System.err.println("Port is not valid! " + port + ", using default value : " + defaultPort);
        }
        return defaultPort;
    }


    /**
     * reads the (user) name from console (to print in front of messages)
     * @return
     */
    public static String getUserName()
    {
        String userName = null;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter your name: ");
        try
        {
            userName = inputReader.readLine();
        }
        catch (IOException e)
        {
            System.out.println("IO-Error: " + e.getMessage());
        }
        return userName;
    }

}
