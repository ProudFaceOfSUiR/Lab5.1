package Commands;
import java.io.IOException;
import java.util.TreeSet;
import com.company.Database;

public class Command {
    String argument;
    public void execute(Database database) throws IOException {
        System.out.println(this.getClass());
    }
    public Command(String argument){
        this.argument = argument;
    }
}
