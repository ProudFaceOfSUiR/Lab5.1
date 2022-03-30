package Commands;

import com.company.Database;
import com.company.Person;
import sun.dc.path.PathError;

public class Add extends Command{
    public Add(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        Person person = database.generateNewElement();
        database.addNewElement(person);
        System.out.println("New person added");
    }
}
