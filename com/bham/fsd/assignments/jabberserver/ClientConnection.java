package com.bham.fsd.assignments.jabberserver;

import java.io.*;
import java.net.*;

public class ClientConnection implements Runnable {

    private Socket clientSocket;
    private JabberDatabase db;
    private String clientUsername;


    public ClientConnection(Socket socket, JabberDatabase database)
    {
        clientSocket = socket;
        db = database;
        new Thread(this).start();
    }
    

    public void run()
    {
        try{

            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            JabberMessage request;

            while((request = (JabberMessage) ois.readObject()) != null)
            {
                System.out.println(request.getMessage());
            
                try {
                    String[] reqSplit = request.getMessage().split(" ", 2);
                    //String reqToken = reqSplit[0];
                    //String data = reqSplit[1];


                    switch (reqSplit[0]) {
                        case "signin":
                            if(checkUsername(reqSplit[1]))
                            {
                                oos.writeObject(new JabberMessage("signedin"));
                                oos.flush();
                                clientUsername = reqSplit[1];
                            }
                            else
                            {
                                oos.writeObject(new JabberMessage("unknown-user"));
                                oos.flush();
                            }
                            break;
                        case "register":
                            String email = reqSplit[1] + "@gmail.com";
                            db.addUser(reqSplit[1], email);
                            oos.writeObject(new JabberMessage("signedin"));
                            oos.flush();
                            clientUsername = reqSplit[1];
                            break;
                        case "signout":
                            break;
                        case "timeline":
                            oos.writeObject(new JabberMessage("timeline", db.getTimelineOfUserEx(clientUsername)));
                            oos.flush();
                            break;
                        case "users":
                            break;
                        case "post":
                            break;
                        case "like":
                            break;
                        case "follow":
                            break;
                        default:
                            //System.out.println("Switch-case detection.");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //System.out.println("Post Switch-case detection.");
            }}
        catch(Exception e)
        {
            e.printStackTrace();
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
