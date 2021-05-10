package com.bham.fsd.assignments.jabberserver;

import java.io.*;
import java.net.*;

public class JabberServer implements Runnable {
    
    private static final int PORT_NUMBER = 44444;
    private ServerSocket serverSocket;

    public JabberServer() {
        try{
            serverSocket = new ServerSocket(PORT_NUMBER);
            serverSocket.setSoTimeout(300);
            System.out.println("Server online.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    public void run() {
        while(true)
        {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");
                ClientConnection client = new ClientConnection(clientSocket, new JabberDatabase());
                Thread.sleep(100);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    public static void main(String arg[])
    {
        new JabberServer();
    }

}
