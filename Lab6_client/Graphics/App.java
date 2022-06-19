package Graphics;


import Network.Client;
import Network.Status;
import Security.LoginController;
import com.company.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    static String test = "failed";
    private static LoginController loginController;
    private static Client client;

    static Stage stage;
    static Language language;

    public Button SignIn;
    public Button SignUp;
    public TextField Log;
    public TextField Pass;
    public ChoiceBox localization;
    public Label password;
    public Label name;
    public Label message;

    public static void initApp(LoginController loginControllerNew, Client clientNew){
        loginController = loginControllerNew;
        client = clientNew;
        language = new Language();
    }


    public void language(ActionEvent actionEvent){
        String str = localization.getValue().toString();
        language.setLang(str);
        name.setText(language.getLoginLoc());
        password.setText(language.getPasswordLoc());
        SignIn.setText(language.getSignInLoc());
        SignUp.setText(language.getSignUpLoc());
        //System.out.println(client.graphicInitialize(loginController));
    }

    public void clickIn(ActionEvent actionEvent) {
        loginController.graphicLogin(Log.getCharacters().toString(), Pass.getCharacters().toString(), false);
        Status stat = client.graphicInitialize(loginController);
        switch(stat) {
            case ESTABLISHED :
            stage.close();
            try{
                DatabaseWindow databaseWindow = new DatabaseWindow();
                databaseWindow.showWindow();
            } catch (Exception e){
                e.printStackTrace();
            }
            break;
            case WRONG_PASSWORD :
                message.setText(language.getWrongLoginLoc());
                break;
            case TAKEN_LOGIN:
                message.setText(language.getTakenLoginLoc());
                break;
            case NOT_ESTABLISHED:
                message.setText(language.getNoServerLoc());
                break;
        }
    }

    public void clickUp(ActionEvent actionEvent) throws  Exception{
        loginController.graphicLogin(Log.getCharacters().toString(), Pass.getCharacters().toString(), true);
        Status stat = client.graphicInitialize(loginController);
        switch(stat) {
            case ESTABLISHED :
                stage.close();
                try{
                    DatabaseWindow databaseWindow = new DatabaseWindow();
                    databaseWindow.showWindow();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case WRONG_PASSWORD :
                message.setText(language.getWrongLoginLoc());
                break;
            case TAKEN_LOGIN:
                message.setText(language.getTakenLoginLoc());
                break;
            case NOT_ESTABLISHED:
                message.setText(language.getNoServerLoc());
                break;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        language.setLang("English (Ind)");
        test = "passed";
        System.out.println("check");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScr.fxml"));
        Pane load = (Pane) loader.load();
        Scene scene = new Scene(load);
        stage=primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Window 1");
        primaryStage.show();
        //System.out.println(test);
    }
    public static void main(String[] args) {
        System.out.println("main");
        launch(args);
    }

}