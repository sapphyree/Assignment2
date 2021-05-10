package com.bham.fsd.assignments.jabberserver;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class testClient {
    
    public static void main(String[] args)
    {
        System.out.println("Client Running.");

        try{
            Socket clientSocket = new Socket("localhost", 44444);

            System.out.println("Socket created.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            JabberMessage response;
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        
            while(!(input = br.readLine()).equals("signout"))
            {
                JabberMessage jm = new JabberMessage(input);

                oos.writeObject(jm);
                oos.flush();

                if((response = (JabberMessage) ois.readObject()) != null)
                {
                    System.out.println(response.getMessage());
                    if(response.getData() != null)
                    {
                        print2(response.getData());
                    }
                }
            }

            oos.writeObject(new JabberMessage("signout"));
            System.out.println("Client closing...");
            clientSocket.close();
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void print2(ArrayList<ArrayList<String>> list) {
		
		for (ArrayList<String> s: list) {
			print1(s);
			System.out.println();
		}
	}

    private static void print1(ArrayList<String> list) {
		
		for (String s: list) {
			System.out.print(s + " ");
		}
	}
}
