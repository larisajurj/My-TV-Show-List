package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.MySqlConnect;
import utilities.UserNameList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    public TextField UsernameField;
    public TextField PasswordField;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Label userLabel;

    public void LoginSuccessfully(ActionEvent event) {
        String enteredUsername = UsernameField.getText();
        String enteredPassword = PasswordField.getText();

        // Retrieve the user information from the database
        MySqlConnect msc = new MySqlConnect();
        ObservableList<UserNameList> userList = msc.getUserInfo();

        // Check if the entered username and password match any user in the database
        for (UserNameList user : userList) {
            if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword)) {
                try {
                    // Load the default scene
                    Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("view/DefaultScene.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(layout);
                    String css = this.getClass().getClassLoader().getResource("css/Style.css").toExternalForm();
                    scene.getStylesheets().add(css);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return; // Exit the method if the login is successful
            }
            else if(!user.getUsername().equals(enteredUsername)){
                userLabel.setText("Username is wrong!");
            } else {
                if(!user.getPassword().equals(enteredPassword)) {
                    userLabel.setText("Password is wrong!");
                }
            }
        }

        // If the login is not successful
        System.out.println("Wrong username or password");
    }
}