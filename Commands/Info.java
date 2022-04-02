package Commands;


import com.company.Database;

public class Info extends Command{

    public Info(String argument) {
        super(argument);
    }
    @Override
    public void execute(Database database){
        System.out.println("Collection type TreeSet, date of initiation: "+database.getCollection().first().getCreationDate().toString()+", length: "+database.getCollection().size());
    }
}
