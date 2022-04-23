import Commands.Command;
import Network.Client;
import com.company.InputReader;
import xmlParser.Parser;

import java.util.Scanner;

public class Main{
    public static void main(String args[]){
        Client client = new Client();
        client.initialize();
        Command command;
        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        while (true){
            command = inputReader.read(scanner);
            client.sendCommand(command);
        }
    }
}

