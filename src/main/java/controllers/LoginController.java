package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public TextField UsernameField;
    public TextField PasswordField;
    private Stage stage;
    private Scene scene;
    private Parent layout;

    public void LoginSuccessfully(ActionEvent event) {

        if (UsernameField.getText().equals(new String("nume")) && PasswordField.getText().equals(new String("1234"))) {
            try {
                Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("view/DefaultScene.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(layout);
                String css = this.getClass().getClassLoader().getResource("css/Style.css").toExternalForm();
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Wrong password");
        }
    }
}