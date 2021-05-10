package com.bham.fsd.assignments.jabberserver;

import java.io.*;
import java.net.*;

public class JabberServer {
    
    private static final int PORT_NUMBER = 44444;
    private ServerSocket serverSocket;


    public JabberServer() {
        try{
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Server online.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public static void main(String arg[])
    {
        new JabberServer();
    }


}
