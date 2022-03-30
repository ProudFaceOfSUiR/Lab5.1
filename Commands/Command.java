package Commands;
import java.util.TreeSet;
import com.company.Database;

public class Command {
    String argument;
    public void execute(Database database){
        System.out.println(this.getClass());
    }
    public Command(String argument){
        this.argument = argument;
    }
}
