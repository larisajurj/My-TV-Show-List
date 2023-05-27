package controllers;

import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.EncryptPassword;
import utilities.MySqlConnect;

import java.io.IOException;

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
        boolean canCreateAccount = true;

        MySqlConnect msc = new MySqlConnect();

        if (UsernameField.getText().isBlank() || PasswordField.getText().isBlank() || ConfirmPasswordField.getText().isBlank()) {
            accountLabel.setText("One of the fields are empty!");
        }
        if (msc.checkUsername(enteredUsername)) {
            accountLabel.setText("Username is taken!");
            canCreateAccount = false;
            return;
        } else {
            if (!enteredPassword.equals(confirmPassword)) {
                accountLabel.setText("Passwords are not the same. Try again!");
                canCreateAccount = false;
                return;
            }
        }
        if (canCreateAccount) {
            msc.insertUserInfo(enteredUsername, encryptedPassword, enteredFavQuote, profilePicture);
            accountLabel.setText("Account created! Go back and log in." );
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
