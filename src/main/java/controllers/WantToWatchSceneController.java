package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.ResourceBundle;

public class WantToWatchSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<TvShowList> table_list;
    @FXML
    private TableColumn<TvShowList, Integer> id;
    @FXML
    private TableColumn<TvShowList, String> title;
    @FXML
    private TableColumn<TvShowList, String> year;
    @FXML
    private TableColumn<TvShowList, String> runtime;
    @FXML
    private TableColumn<TvShowList, String> rating;
    @FXML
    private TableColumn<TvShowList, String> genre;
    @FXML
    private TableColumn<TvShowList, String> description;
    ObservableList<TvShowList> listM;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void goToWatchedScene(ActionEvent event) {
        try {
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("view/WatchedScene.fxml"));
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
        id.setCellValueFactory(new PropertyValueFactory<TvShowList, Integer>("id"));
        title.setCellValueFactory(new PropertyValueFactory<TvShowList, String>("title"));
        year.setCellValueFactory(new PropertyValueFactory<TvShowList, String>("year"));
        runtime.setCellValueFactory(new PropertyValueFactory<TvShowList, String>("runtime"));
        rating.setCellValueFactory(new PropertyValueFactory<TvShowList, String>("rating"));
        genre.setCellValueFactory(new PropertyValueFactory<TvShowList, String>("genre"));
        description.setCellValueFactory(new PropertyValueFactory<TvShowList, String>("text"));
        listM = MySqlConnect.getDataShows();
        table_list.setItems(listM);
    }
}
