package Commands;

import com.company.Database;
import com.company.Person;

/**
 * Prints every Person, where name starts with special String
 */
public class FilterStartsWithName extends Command{
    public FilterStartsWithName(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){}
}
