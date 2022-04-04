package Commands;

import com.company.Database;
import com.company.Person;

public class Add extends Command{
    /**
     * adds new Person
     * @param argument
     */
    public Add(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        Person person = database.generateNewElement(-1L);
        database.addNewElement(person);
        System.out.println("New person added");
    }
}
