package Commands;

import com.company.Database;

import java.io.Serializable;

/**
 * executed then the input cannot be understood
 */
public class Error extends Command implements Serializable {
    public Error(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.out.println("Unknown command, try help");
    }
}