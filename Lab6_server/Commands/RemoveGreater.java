package Commands;

import com.company.Database;
import com.company.Person;

/**
 * Removes every element with ID Higher than provided
 */
public class RemoveGreater extends Command {
    public RemoveGreater(String argument) {
        super(argument);
    }

    @Override
    public void execute(Database database) {
        try {
            if (argument == null) throw new NullPointerException();
            for (Person p:database.getCollection())
                if(p.getId() > (Long.parseLong(argument)) && p.getUserName().equals(database.getLogin()))
                    database.getDatabaseManager().removeElementFromDatabase(p);
            database.getCollection().removeIf(p -> ((p.getId() > Long.parseLong(argument)) && (p.getUserName().equals(database.getLogin()))));
        } catch (NumberFormatException numberFormatException) {
            answer = "No arguments";
        }
    }
}
