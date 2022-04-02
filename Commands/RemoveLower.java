package Commands;

import com.company.Database;

public class RemoveLower extends Command{
    public RemoveLower(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
        try {
            database.getCollection().removeIf(p -> p.getId() < (Long.parseLong(argument)));
        }catch (NumberFormatException numberFormatException){
            System.out.println("No arguments");
        }
    }
}
