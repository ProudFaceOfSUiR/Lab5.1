package Commands;

import com.company.Database;

public class Show extends Command{
    public Show(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.out.println(database);
    }
}
