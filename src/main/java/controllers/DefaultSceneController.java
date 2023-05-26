package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DefaultSceneController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label usernameLabel;
    public void displayUsername(String username){
        usernameLabel.setText(username);

    }
    public void goToWantToWatchScene(ActionEvent event) {
        try {
            FXMLLoader loaderWatched = new FXMLLoader(getClass().getClassLoader().getResource("view/WantToWatchScene.fxml"));
            Parent layout = loaderWatched.load();
            WatchedSceneController watchedSceneController =loaderWatched.getController();
            watchedSceneController.displayUsername(usernameLabel.getText());
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
            WatchedSceneController watchedSceneController =loaderWatched.getController();
            watchedSceneController.displayUsername(usernameLabel.getText());
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
}
