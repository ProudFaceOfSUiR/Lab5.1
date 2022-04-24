package Commands;

import com.company.Database;

import java.io.Serializable;

/**
 * Prints story of executed commands
 */
public class History extends Command implements Serializable {
    public History(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        answer = database.getHistoryLog();
    }
}
