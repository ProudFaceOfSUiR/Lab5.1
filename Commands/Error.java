package Commands;

import com.company.Database;

public class Error extends Command{
    public Error(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.out.println("Unknown command, try help");
    }
}
