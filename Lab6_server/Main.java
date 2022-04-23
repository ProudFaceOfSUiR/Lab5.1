import Commands.Command;
import com.company.Database;
import com.company.InputReader;
import xmlParser.Parser;
import java.util.Scanner;

public class Main {
    /**
     * @author Alexandr Grebtsov
     * Main class
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        Database Database = new Database();
        Database.initialize();
        if(args.length>0) Parser.parseFromXML(Database,args[0]);
        else System.out.println("No file");
        while(true) {
            try {
                Command command = inputReader.read(scanner);
                command.execute(Database);
                Database.updateHistoryLog(command);
            }catch (Exception ignored){}
        }
    }
}
