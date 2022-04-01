package Commands;

import com.company.Database;
import com.company.Person;

public class FilterLessThanLocation extends Command{
    public FilterLessThanLocation(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        try {
            for (Person person:database.getCollection()){
                if(person.getLocation().compareTo(Float.parseFloat(argument))>0)
                    System.out.println(person);
            }
        }
        catch (NullPointerException e){
            System.out.println("No argument");
        }
    }
}
