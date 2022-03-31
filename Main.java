import Commands.Command;
import com.company.Database;
import com.company.InputReader;
import xmlParser.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        Database Database = new Database();
        Database.initialize();
        Parser.parseFromXML(Database,args[0]);
        while(true) {
            Command command = inputReader.read(scanner);
            command.execute(Database);
            Database.updateHistoryLog(command);
        }
    }
}
