package com.bham.fsd.assignments.jabberserver;

import java.io.*;
import java.net.*;

public class ClientConnection implements Runnable {

    private Socket clientSocket;
    private JabberDatabase db;


    public ClientConnection(Socket socket, JabberDatabase database)
    {
        clientSocket = socket;
        db = database;
        new Thread(this).start();
    }

    public void run()
    {
        while(true)
        {
            try {
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                JabberMessage request = (JabberMessage) ois.readObject();
                String reqToken = request.getMessage().split("\\s+")[0];

                switch (reqToken) {
                    case "signin":
                        if(checkUsername(reqToken));
                        {
                            oos.writeObject(new JabberMessage("signedin"));
                            oos.flush();
                        }
                        break;
                
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Boolean checkUsername(String username)
    {
        if(db.getUserID(username) != -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
