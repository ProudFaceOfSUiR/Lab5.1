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
        String db = Parser.parseToXML(database);
        Parser.dataBasetoXML(db,"/Users/anatoly_novikov/IdeaProjects/Oh,_shit_here_we_go_again/src/test2.xml");

    }
}
