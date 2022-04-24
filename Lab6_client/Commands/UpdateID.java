package Commands;

import com.company.Database;
import com.company.Person;

/**
 * Changes values of the element via ID
 */
public class UpdateID extends Command{
    Person person;
    public UpdateID(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        person = database.generateNewElement(-1L);
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
