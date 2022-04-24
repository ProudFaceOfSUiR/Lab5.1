package Commands;

import Exceptions.RecursionException;
import com.company.Database;
import com.company.Person;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
