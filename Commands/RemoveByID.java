package Commands;

import com.company.Database;

public class RemoveByID extends Command{
    public RemoveByID(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
        try {
            if (database.getCollection().removeIf(p -> p.getId().equals(Long.parseLong(argument))))
                System.out.println("Element successfully removed");
            else
                System.out.println("No such ID");
        }catch (NullPointerException | NumberFormatException e){
            System.out.println("No argument");
        }
    }
}
