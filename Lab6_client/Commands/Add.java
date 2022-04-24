package Commands;

import com.company.Database;
import com.company.Person;

import java.io.Serializable;

public class Add extends Command {
    /**
     * adds new Person
     * @param argument
     */
    private Person person;
    public Add(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        Person person = database.generateNewElement(-1L);
        this.person = person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
