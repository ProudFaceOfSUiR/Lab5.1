package Graphics;

import com.company.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseViewController {


    private ObservableList<User> usersData = FXCollections.observableArrayList();
    private ObservableList<Person> personsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Person> tableUsers;

    @FXML
    private TableColumn<Person, Long> idColumn;

    @FXML
    private TableColumn<Person, String> loginColumn;

    @FXML
    private TableColumn<Person, String> passwordColumn;

    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));

        // заполняем таблицу данными
        tableUsers.setItems(personsData);
    }

    @FXML
    private void addNew(){
        System.out.println("add");

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

}