package Commands;

import com.company.Database;
import com.company.Person;
/**
 * Prints every Person with total location sum less than provided
 */
public class FilterLessThanLocation extends Command{
    public FilterLessThanLocation(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){}
}
