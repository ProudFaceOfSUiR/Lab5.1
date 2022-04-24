package Commands;

import com.company.Database;

/**
 * Removes an element from collection via ID
 */
public class RemoveByID extends Command{
    public RemoveByID(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
    }
}
