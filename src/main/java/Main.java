import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    Stage window;
    Scene startingScene;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //here will be the main javafx code
        try {
            window = primaryStage;
            Parent layout1 = FXMLLoader.load(getClass().getClassLoader().getResource("view/LoginScene.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/LoginScene.fxml"));
            //Parent layout1 = loader.load();
            startingScene = new Scene(layout1);
            Image icon = new Image("images/icon.png");
            window.getIcons().add(icon);
            String css = this.getClass().getClassLoader().getResource("css/Style.css").toExternalForm();
            startingScene.getStylesheets().add(css);
            window.setScene(startingScene);
            window.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
