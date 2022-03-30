package Commands;

import com.company.Database;

public class History extends Command{
    public History(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.out.println(database.getHistoryLog());
    }
}
