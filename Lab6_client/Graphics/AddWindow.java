package Graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddWindow extends Application {
    Stage stage=new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("addNew.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public void  showWindow() throws Exception {
        start(stage);
    }
}
