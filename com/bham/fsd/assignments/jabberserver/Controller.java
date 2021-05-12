package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

public class Controller {
  
    @FXML
    TextField T1;
  
    @FXML
    public void signInButton(ActionEvent event)
    {
     System.out.println(T1.getText());
     Socket clientSocket = new Socket("localhost", 44444);
     ObjectOutputStream oof = new ObjectOutputStream(clientSocket.getInputStream());
     String signInInput = T1.getText();
     oof.writeObject();
    }
}
