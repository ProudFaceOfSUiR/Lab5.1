package Graphics;

import com.company.Person;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class DatabaseWindow extends Application {

    Stage stage=new Stage();

    private ObservableList<User> usersData = FXCollections.observableArrayList();
    private ObservableList<Person> personsData = FXCollections.observableArrayList();
    @FXML
    private TableView<Person> tableUsers;
    @FXML
    private TableColumn<Person, Long> idColumn;
    public TableColumn<Person, String> nameColumn;
    public TableColumn<Person, String> usernameColumn;
    public TableColumn<Person, String> coordXColumn;
    public TableColumn<Person, String> coordYColumn;
    public TableColumn<Person, String> dateColumn;
    public TableColumn<Person, String> heightColumn;
    public TableColumn<Person, String> eyeColumn;
    public TableColumn<Person, String> hairColumn;


    private void initialize() {
        System.out.println("bla");
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке


        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));
        coordYColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("coordinates.getX"));
        coordYColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("coordinates.getY"));
        //dateColumn.setCellValueFactory(new PropertyValueFactory<Person, String>());

        // заполняем таблицу данными
        tableUsers.setItems(personsData);
    }

    @FXML
    private void addNew() throws Exception {
        System.out.println("add");
        AddWindow addWindow = new AddWindow();
        addWindow.showWindow();

    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {
        personsData.add(new Person(1l, "username", "justname", 1, 2.0F,null, 1, Person.Color.RED, Person.Color.BLACK, Person.Country.USA, null,1,null));
    }

    public void goToWindow2(ActionEvent event) {
        try {
            new DatabaseWindow();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        initialize();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }
    public void  showWindow() throws Exception {
        start(stage);
    }
}
