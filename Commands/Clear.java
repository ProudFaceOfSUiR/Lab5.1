package Commands;

import com.company.Database;

public class Clear extends Command{
    public Clear(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        database.clearCollection();
        System.out.println("Collection successfully cleared");
    }
}
