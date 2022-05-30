package Graphics;

import com.company.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LoginViewController {

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
        try{
            DatabaseWindow databaseWindow = new DatabaseWindow();
            databaseWindow.showWindow();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
