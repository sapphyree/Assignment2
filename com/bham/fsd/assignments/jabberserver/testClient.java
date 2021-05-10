package com.bham.fsd.assignments.jabberserver;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class testClient {
    
    public static void main(String[] args) throws Exception
    {
        System.out.println("Client Running.");

        Socket clientSocket = new Socket("localhost", 44444);

        System.out.println("Socket created.");
        Scanner s = new Scanner(System.in);
        while(true)
        {
            String input = s.nextLine();
            JabberMessage jm = new JabberMessage(input);

            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(jm);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            JabberMessage response = (JabberMessage) ois.readObject();
            System.out.println(response.getMessage());
        }

        

        //clientSocket.close();

    }
}
