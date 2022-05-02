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
        person.setId(database.generateID());
        database.addNewElement(person);
        System.out.println(database);
        this.answer = person.toString();
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
