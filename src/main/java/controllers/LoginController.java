package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.EncryptPassword;
import utilities.MySqlConnect;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField PasswordField;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Label userLabel;
    private EncryptPassword encryptPassword = new EncryptPassword();

    public void LoginSuccessfully(ActionEvent event) {
        String enteredUsername = UsernameField.getText();
        String enteredPassword = PasswordField.getText();
        String encryptedPassword = encryptPassword.encrypt(enteredPassword);

        // Retrieve the user information from the database
        MySqlConnect msc = new MySqlConnect();

        // Check if the entered username and password match any user in the database
        if(UsernameField.getText().isBlank() || PasswordField.getText().isBlank()) {
            userLabel.setText("You have to fill in both fields!");
        }
        if (msc.checkPassword(encryptedPassword, enteredUsername) && msc.checkUsername(enteredUsername)) {
            try {
                // Load the default scene
                msc.setActiveSession(enteredUsername);
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
        else if(!msc.checkUsername(enteredUsername)){
            userLabel.setText("Username is wrong!");
        } else {
            if(!msc.checkPassword(encryptedPassword, enteredUsername)) {
                userLabel.setText("Password is wrong!");
            }
        }
    }

    public void goSignUp(ActionEvent event) {
        try {
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("view/SignUpScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(layout);
            String css = this.getClass().getClassLoader().getResource("css/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}