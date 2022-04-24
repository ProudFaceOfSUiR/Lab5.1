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
        try {
            if(argument == null) throw new NullPointerException();
            if (database.getCollection().removeIf(p -> p.getId().equals(Long.parseLong(argument)))){
                person.setId(Long.parseLong(argument));
                database.addNewElement(person);
            }
            else answer = "No such element";
        }catch (NullPointerException|NumberFormatException e){
            answer = "No argument";
        }
    }
    public void setPerson(Person person) {
        this.person = person;
    }
}
