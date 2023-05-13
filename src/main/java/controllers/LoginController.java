package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class LoginController {

    public TextField UsernameField;
    public TextField PasswordField;


    public void LoginSuccessfully(ActionEvent event){
       System.out.println("Username is: " + UsernameField.getText());
       System.out.println("Username is: " + PasswordField.getText());

    }



    }
