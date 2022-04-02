package Commands;

import com.company.Database;
import xmlParser.Parser;

import java.io.IOException;

public class Save extends Command{
    public Save(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) throws IOException {
        try {
            String db = Parser.parseToXML(database);
            Parser.dataBasetoXML(db, argument);
        } catch (NullPointerException nullPointerException){
            System.out.println("No argument");
        }
    }
}
