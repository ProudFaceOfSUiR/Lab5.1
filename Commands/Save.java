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
        //Parser.parse(database,"/Users/anatoly_novikov/IdeaProjects/Oh,_shit_here_we_go_again/src/test.xml");


    }
}
