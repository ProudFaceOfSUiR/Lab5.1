package Commands;

import com.company.Database;
import com.company.Person;

/**
 * clears collection
 */
public class Clear extends Command {
    public Clear(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        for (Person p:database.getCollection())
            if(p.getUserName().equals(database.getLogin()))
                database.getDatabaseManager().removeElementFromDatabase(p);
        database.getCollection().removeIf(p -> p.getUserName().equals(database.getLogin()));
        answer = "Collection successfully cleared";
    }
}
