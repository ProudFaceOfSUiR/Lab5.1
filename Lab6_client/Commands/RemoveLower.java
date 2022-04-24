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
    public void execute(Database database) {}
}
