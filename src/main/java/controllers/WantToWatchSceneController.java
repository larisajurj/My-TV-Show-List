package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utilities.MySqlConnect;
import utilities.TvShowList;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class WantToWatchSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<TvShowList> table_list;
    @FXML
    private TableColumn<TvShowList, String> col_title;
    @FXML
    private TableColumn<TvShowList, String> col_year;
    @FXML
    private TableColumn<TvShowList, String> col_runtime;
    @FXML
    private TableColumn<TvShowList, String> col_rating;
    @FXML
    private TableColumn<TvShowList, String> col_genre;
    @FXML
    private TableColumn<TvShowList, String> col_description;
    @FXML
    private Button plusButton;
    @FXML
    private Label usernameLabel;

    public void displayUsername(String username){
        usernameLabel.setText(username);

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
    public void addButton(ActionEvent event){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MySqlConnect msc = new MySqlConnect();
        ObservableList<TvShowList> listM = MySqlConnect.getDataShows();
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_runtime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("text"));
        /*plusButton.setOnAction(event -> {
            TvShowList selectedMovie = table_list.getSelectionModel().getSelectedItem();
            if (selectedMovie != null) {
                System.out.println("Selected Movie: " + selectedMovie.getTitle());
                msc.addToWantToWatch("nume", selectedMovie.getTitle() );
            } else {
                System.out.println("No movie selected.");
            }
        });*/
        table_list.setItems(listM);
    }

}
