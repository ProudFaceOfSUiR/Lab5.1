package Commands;

import com.company.Database;
import com.company.Person;

/**
 * Prints every Person, where name starts with special String
 */
public class FilterStartsWithName extends Command{
    public FilterStartsWithName(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        try {
            if(argument == null) throw new NullPointerException();
            boolean contains = false;
            for (Person p : database.getCollection()) {
                if (p.getName().startsWith(argument)&&!argument.equals("")) {
                    answer+=p;
                    contains = true;
                }
            }
            if (!contains)
                answer = "No such elements";
        }catch (NullPointerException exception)
        {
            answer = "No argument";
        }
    }
}
