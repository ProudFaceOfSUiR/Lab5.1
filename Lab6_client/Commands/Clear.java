package Commands;

import com.company.Database;


/**
 * clears collection
 */
public class Clear extends Command {
    public Clear(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){}
}
