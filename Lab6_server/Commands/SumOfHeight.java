package Commands;

import com.company.Database;

/**
 * Prints total height of every element
 */
public class SumOfHeight extends Command{
    public SumOfHeight(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.out.println("Total height is " + database.sumOfHeight());
    }
}
