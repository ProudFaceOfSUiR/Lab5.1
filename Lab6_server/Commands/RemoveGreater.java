package Commands;

import com.company.Database;

/**
 * Removes every element with ID Higher than provided
 */
public class RemoveGreater extends Command{
    public RemoveGreater(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
        try {
            if (argument == null) throw new NullPointerException();
            database.getCollection().removeIf(p -> p.getId() > (Long.parseLong(argument)));
        }catch (NumberFormatException numberFormatException){
            answer = "No arguments";
    }
    }

}
