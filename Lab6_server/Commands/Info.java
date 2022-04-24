package Commands;


import com.company.Database;

/**
 * Prints info about collection
 */
public class Info extends Command{

    public Info(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database) {
        if (database.getCollection().size() > 0)
            answer = "Collection type TreeSet, date of initiation: " +
                    database.getCollection().first().getCreationDate().toString() + ", length: " +
                    database.getCollection().size();
        else
            answer = "Collection type TreeSet, date of initiation: " +
                     ", length: " +
                    database.getCollection().size();
    }
}
