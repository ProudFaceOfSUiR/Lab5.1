package Commands;

import java.io.IOException;
import java.io.Serializable;
import com.company.Database;

/**
 * Parent class for every command
 */
public class Command implements Serializable {
    String argument;
    String answer;
    public void execute(Database database) throws IOException {
        System.out.println(this.getClass());
    }
    public Command(String argument){
        this.argument = argument;
    }

    public String getAnswer() {
        return answer;
    }
}
