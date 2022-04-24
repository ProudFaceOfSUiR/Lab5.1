package Commands;

import com.company.Database;

/**
 * Removes an element from collection via ID
 */
public class RemoveByID extends Command{
    public RemoveByID(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
        try {
            if(argument == null) throw new NullPointerException();
            if (database.getCollection().removeIf(p -> p.getId().equals(Long.parseLong(argument))))
                answer = "Element successfully removed";
            else
                answer = "No such ID";
        }catch (NullPointerException | NumberFormatException e){
            answer = "No argument";
        }
    }
}
