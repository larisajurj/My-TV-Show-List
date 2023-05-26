package controllers;

import javafx.scene.Parent;
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
import utilities.EncryptPassword;
import utilities.MySqlConnect;
import utilities.UserNameList;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {
    public TextField UsernameField;
    public TextField PasswordField;
    public TextField FavoriteQuoteField;
    public TextField ConfirmPasswordField;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Label accountLabel;
    private EncryptPassword encryptPassword = new EncryptPassword();
    private String profilePicture = "1";

    public void createAccount(ActionEvent event) {
        String enteredUsername = UsernameField.getText();
        String enteredPassword = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String enteredFavQuote = FavoriteQuoteField.getText();
        String encryptedPassword = encryptPassword.encrypt(enteredPassword);

        MySqlConnect msc = new MySqlConnect();
        ObservableList<UserNameList> userList = MySqlConnect.getUserInfo();

        for(UserNameList user : userList) {
            if(user.getUsername().equals(enteredUsername)) {
                accountLabel.setText("Username is taken!");
                return;
            }
            else {
                if(!enteredPassword.equals(confirmPassword)) {
                    accountLabel.setText("Passwords are not the same. Try again!");
                    return;
                }
                else {
                    try {
                        Connection conn = MySqlConnect.ConnectDb();
                        PreparedStatement ps = conn.prepareStatement("INSERT INTO user_table (user, password, favQuote, profilePic) VALUES (?, ?, ?, ?)");
                        ps.setString(1, enteredUsername);
                        ps.setString(2, encryptedPassword);
                        ps.setString(3, enteredFavQuote);
                        ps.setString(4, Integer.valueOf(profilePicture).toString());
                        ps.executeUpdate();
                        System.out.println(encryptedPassword);


                        accountLabel.setText("Account created! Go back and log in.");
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                        System.out.println(sqlException.getMessage());
                    }
                }
            }
        }
    }

    public void goBack(ActionEvent event) {
        try {
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("view/LoginScene.fxml"));
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
    public void profileOne(ActionEvent event) {
        profilePicture = "1";
    }
    public void profileTwo(ActionEvent event) {
        profilePicture = "2";
    }
    public void profileThree(ActionEvent event) {
        profilePicture = "3";
    }
    public void profileFour(ActionEvent event) {
        profilePicture = "4";
    }
    public void profileFive(ActionEvent event) {
        profilePicture = "5";
    }
    public void profileSix(ActionEvent event) {
        profilePicture = "6";
    }
}
