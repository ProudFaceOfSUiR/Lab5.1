package Commands;

import com.company.Database;

/**
 * Providing exit from program
 */
public class Exit extends Command{
    public Exit(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.exit(0);
    }
}
