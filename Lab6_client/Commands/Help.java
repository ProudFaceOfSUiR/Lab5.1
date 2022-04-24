package Commands;

import com.company.Database;

import java.io.Serializable;

/**
 * Just help
 */
public class Help extends Command implements Serializable {
    public Help(String argument) {
        super(argument);
    }

    @Override
    public void execute(Database database) {}

}
