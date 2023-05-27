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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.MySqlConnect;
import utilities.TvShowList;

import java.net.URL;
import java.util.ResourceBundle;

public class AddToWatchedListController implements Initializable{
    private String username;
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
    private TextField searchField;
    private FilteredList<TvShowList> filteredData;
    @FXML
    private Label statusLabel;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        MySqlConnect msc = new MySqlConnect();
        ObservableList<TvShowList> listM = MySqlConnect.getDataShows();
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_runtime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("text"));
        plusButton.setOnAction(event -> {
            TvShowList selectedMovie = table_list.getSelectionModel().getSelectedItem();
            if (selectedMovie != null) {
                //System.out.println("Selected Movie: " + selectedMovie.getTitle());
                statusLabel.setText(msc.addToWatched(msc.getActiveSession(), selectedMovie.getTitle()));
            } else {
                statusLabel.setText("No movie selected.");
            }
        });
        table_list.setItems(listM);
        ObservableList<TvShowList> dataList = table_list.getItems();
        filteredData = new FilteredList<>(dataList, p -> true);

        // Bind the filtered data to the table view
        table_list.setItems(filteredData);

        // Add a listener to the search field text property
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tvShow -> {
                // If search field is empty, show all items
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare the title with the search query (case-insensitive)
                String lowerCaseQuery = newValue.toLowerCase();
                return tvShow.getTitle().toLowerCase().contains(lowerCaseQuery);
            });
        });
    }
    public void goToCustom(ActionEvent event){
        try {
            FXMLLoader loaderWatched = new FXMLLoader(getClass().getClassLoader().getResource("view/AddCustomWantToWatchScene.fxml"));
            Parent layout = loaderWatched.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(layout);
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
}
