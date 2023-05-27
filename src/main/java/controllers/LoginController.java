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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utilities.EncryptPassword;
import utilities.MySqlConnect;
import utilities.UserNameList;
import java.io.IOException;

public class LoginController{
    public TextField UsernameField;
    public TextField PasswordField;
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
        //MySqlConnect msc = new MySqlConnect();
        ObservableList<UserNameList> userList = MySqlConnect.getUserInfo();

        // Check if the entered username and password match any user in the database
        for (UserNameList user : userList) {
            if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(encryptedPassword)) {
                try {
                    MySqlConnect msc = new MySqlConnect();
                    msc.setActiveSession(enteredUsername);
                    // Load the default scene

                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/DefaultScene.fxml"));
                    layout = loader.load();
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