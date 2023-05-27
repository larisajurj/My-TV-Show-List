package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import utilities.MySqlConnect;

public class AddCustomWantToWatchSceneController {
    @FXML
    private TextField descriptionField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField ratingField;

    @FXML
    private TextField runtimeField;

    @FXML
    private TextField titleField;

    @FXML
    private HBox titleLabel;

    @FXML
    private TextField yearField;
    @FXML
    private Label statusLabel;

    @FXML
    void addCustom(ActionEvent event) {
        MySqlConnect msc = new MySqlConnect();
        statusLabel.setText(msc.addCustomTvShowToWantToWatch(msc.getActiveSession(), titleField.getText(), yearField.getText(), runtimeField.getText(), ratingField.getText(), genreField.getText(), descriptionField.getText()));
    }
}
