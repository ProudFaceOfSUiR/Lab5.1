import Commands.Command;
import Network.Client;
import com.company.Database;
import com.company.InputReader;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String args[]) throws IOException {
        Client client = new Client();
        client.initialize();
        Database database = new Database();
        database.initialize();
        Command command;
        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        while (true){
            command = inputReader.read(scanner);
            command.execute(database);
            client.sendCommand(command);
            command = client.recieveCommand();
            System.out.println(command.getAnswer());
        }
    }
}

