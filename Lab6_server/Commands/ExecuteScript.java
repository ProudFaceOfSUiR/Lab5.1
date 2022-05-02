package Commands;
import com.company.Database;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Executes script, every command starts with new line
 */
public class ExecuteScript extends Command {
    LinkedList<Command> commands = new LinkedList<>();
    public ExecuteScript(String argument) {
        super(argument);
    }

    @Override
    public void execute(Database datebase) throws IOException {
        for (Command command : commands){
            command.execute(datebase);
            answer+=command.getAnswer();
        }
    }
}
