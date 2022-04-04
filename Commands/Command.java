package Commands;
import java.io.IOException;
import com.company.Database;

/**
 * Parent class for every command
 */
public class Command {
    String argument;
    public void execute(Database database) throws IOException {
        System.out.println(this.getClass());
    }
    public Command(String argument){
        this.argument = argument;
    }
}
