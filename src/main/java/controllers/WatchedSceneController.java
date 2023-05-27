package controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utilities.MySqlConnect;
import utilities.TvShowList;

import java.net.URL;
import java.util.ResourceBundle;

public class WatchedSceneController implements Initializable{
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
    private Label usernameLabel;
    private MySqlConnect msc;
    private FilteredList<TvShowList> filteredData;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField titleTextField;
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
    public void plusButton(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/AddToWatchedScene.fxml"));
            Parent layout = loader.load();

            stage = new Stage();
            stage.setTitle("Add to list!");
            scene = new Scene(layout);
            Image icon = new Image("images/icon.png");
            stage.getIcons().add(icon);
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
        msc = new MySqlConnect();
        String username = msc.getActiveSession();
        usernameLabel.setText("@"+username);
        quote.setText(msc.getFavouriteQuote(username));
        String pathToImage = "images/avatars/profile" + msc.getProfilePic(username) + ".png";
        Image profile = new Image(pathToImage);
        avatar.setImage(profile);
        ObservableList<TvShowList> listM = MySqlConnect.getWatchedData(username);
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_runtime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("text"));
        table_list.setItems(listM);

        ObservableList<TvShowList> dataList = table_list.getItems();
        filteredData = new FilteredList<>(dataList, p -> true);
        table_list.setItems(filteredData);
        titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tvShow -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseQuery = newValue.toLowerCase();
                return tvShow.getTitle().toLowerCase().contains(lowerCaseQuery);
            });
        });
        genreTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tvShow -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseQuery = newValue.toLowerCase();
                return tvShow.getGenre().toLowerCase().contains(lowerCaseQuery);
            });
        });
    }


}
