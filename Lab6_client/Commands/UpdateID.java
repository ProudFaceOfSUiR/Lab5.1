package Commands;

import com.company.Database;

/**
 * Changes values of the element via ID
 */
public class UpdateID extends Command{
    public UpdateID(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        try {
            if (database.getCollection().removeIf(p -> p.getId().equals(Long.parseLong(argument))))
                database.addNewElement(database.generateNewElement(Long.parseLong(argument)));
            else System.out.println("No such element");
        }catch (NullPointerException|NumberFormatException e){
            System.out.println("No argument");
        }
    }
}
