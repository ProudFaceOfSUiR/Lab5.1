package Graphics;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    static Stage stage;

    @FXML
    public Button SignIn;
    @FXML
    public Button SignUp;
    @FXML
    public TextField Log;
    @FXML
    public TextField Pass;

    public void clickIn(ActionEvent actionEvent) {
        System.out.println("in");
        System.out.println(Log.getCharacters());
        System.out.println(Pass.getCharacters());
    }

    public void clickUp(ActionEvent actionEvent) throws  Exception{
        System.out.println("up");
        System.out.println(Log.getCharacters());
        System.out.println(Pass.getCharacters());
        stage.close();
        try{
            DatabaseWindow databaseWindow = new DatabaseWindow();
            databaseWindow.showWindow();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScr.fxml"));
        Pane load = (Pane) loader.load();
        Scene scene = new Scene(load);
        stage=primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Window 1");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}