package Commands;

import com.company.Database;

/**
 *Removes every element lower than provided
 */
public class RemoveLower extends Command{
    public RemoveLower(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
        try {
            if (argument == null) throw new NullPointerException();
            database.getCollection().removeIf(p -> p.getId() < (Long.parseLong(argument)));
        }catch (NumberFormatException numberFormatException){
            answer = "No arguments";
        }
    }
}
