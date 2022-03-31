package Commands;

import com.company.Database;
import com.company.Person;

public class SumOfHeight extends Command{
    public SumOfHeight(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.out.println("Total height is " + database.sumOfHeight());
    }
}
