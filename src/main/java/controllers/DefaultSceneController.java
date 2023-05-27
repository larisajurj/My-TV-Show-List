package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utilities.MySqlConnect;
import utilities.UserSession;
import javafx.scene.image.Image;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DefaultSceneController implements Initializable{
    private Stage stage;
    private Scene scene;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label quote;
    @FXML
    private ImageView avatar;

    public void goToWantToWatchScene(ActionEvent event) {
        try {
            FXMLLoader loaderWatched = new FXMLLoader(getClass().getClassLoader().getResource("view/WantToWatchScene.fxml"));
            Parent layout = loaderWatched.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(layout);
            String css = this.getClass().getClassLoader().getResource("css/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToWatchedScene(ActionEvent event) {
        try {
            FXMLLoader loaderWatched = new FXMLLoader(getClass().getClassLoader().getResource("view/WatchedScene.fxml"));
            Parent layout = loaderWatched.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(layout);
            String css = this.getClass().getClassLoader().getResource("css/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MySqlConnect msc = new MySqlConnect();
        String username = msc.getActiveSession();
        usernameLabel.setText("@"+username);
        //System.out.println(msc.getProfilePic(username));
        quote.setText(msc.getFavouriteQuote(username));
        String pathToImage = "images/avatars/profile" + msc.getProfilePic(username) + ".png";
        Image profile = new Image(pathToImage);
        avatar.setImage(profile);

    }
}
