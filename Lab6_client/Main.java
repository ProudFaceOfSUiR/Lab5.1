import Commands.Command;
import Network.Client;
import com.company.Database;
import com.company.InputReader;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main{
    public static void main(String args[]) throws IOException, InterruptedException {
        Client client = new Client();
        client.initialize();
        Database database = new Database();
        database.initialize();
        Command command;
        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        while (true){
            try {
                command = inputReader.read(scanner);
                command.execute(database);
                client.sendCommand(command);
                command = client.recieveMessage().getCommand();
                System.out.println(command.getAnswer());
            } catch (SocketTimeoutException | NullPointerException e){
                System.out.println("Unable to connect to server");
                client.initialize();
            }
        }
    }
}

