package Commands;

import com.company.Database;
import xmlParser.Parser;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Save extends Command{
    public Save(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) throws IOException {
        try {
            System.out.println("Confirm Y/n");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().toUpperCase(Locale.ROOT).equals("Y")){
                String db = Parser.parseToXML(database);
                Parser.dataBasetoXML(db, argument);
            }else System.out.println("Operation aborted");
        } catch (NullPointerException nullPointerException){
            System.out.println("No argument");
        }
    }
}
