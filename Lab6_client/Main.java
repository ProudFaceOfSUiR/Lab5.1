import Commands.Command;
import Graphics.App;
import Network.Client;
import Security.LoginController;
import com.company.Database;
import com.company.InputReader;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main{
    public static void main(String args[]) throws IOException, InterruptedException {
        Client client = new Client(228);

        Database database = new Database();
        LoginController loginController = new LoginController();
        database.initialize(loginController);
        Command command;
        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader();

        //App app = new App("bla");
        App.initApp(loginController,client);
        App.main(args);
        /*
        client.initialize(loginController);


        while (true){
            try {
                command = inputReader.read(scanner);
                command.execute(database);
                client.sendCommand(command);
                command = client.recieveMessage().getCommand();
                System.out.println(command.getAnswer());
            } catch (SocketTimeoutException | NullPointerException e){
                System.out.println("Unable to connect to server");
                //e.printStackTrace();
                client.initialize(loginController);
            }
        }*/
    }
}

