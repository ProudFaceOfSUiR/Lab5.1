package Commands;

import com.company.Database;
import com.company.Person;

public class FilterStartsWithName extends Command{
    public FilterStartsWithName(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        try {
            boolean contains = false;
            for (Person p : database.getCollection()) {
                if (p.getName().startsWith(argument)) {
                    System.out.println(p);
                    contains = true;
                }
            }
            if (!contains)
                System.out.println("No such elements");
        }catch (NullPointerException exception)
        {
            System.out.println("No argument");
        }
    }
}
