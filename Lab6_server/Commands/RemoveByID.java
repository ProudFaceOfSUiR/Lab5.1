package Commands;

import com.company.Database;
import com.company.Person;

/**
 * Removes an element from collection via ID
 */
public class RemoveByID extends Command{
    public RemoveByID(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
        try {
            if(argument == null) throw new NullPointerException();
            for (Person p:database.getCollection())
                if(p.getId().equals(Long.parseLong(argument)) && p.getUserName().equals(database.getLogin()))
                    database.getDatabaseManager().removeElementFromDatabase(p);
            if (database.getCollection().removeIf((p -> p.getId().equals(Long.parseLong(argument)) && p.getUserName().equals(database.getLogin()))))
                answer = "Element successfully removed";
            else
                answer = "No such ID";
        }catch (NullPointerException | NumberFormatException e){
            answer = "No argument";
        }
    }
}
