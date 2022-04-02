package Commands;

import com.company.Database;
import com.company.InputReader;
import com.company.Person;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ExecuteScript extends Command {
    public ExecuteScript(String argument) {
        super(argument);
    }

    @Override
    public void execute(Database datebase) {
        try {
            File file = new File(argument);
            Scanner scanner = new Scanner(file);
            InputReader inputReader = new InputReader();
            while (scanner.hasNext()) {
                Command command = inputReader.read(scanner);
                if (command.getClass().toString().equals("Commands.Add")){
                    SimpleDateFormat formatter =new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
                    datebase.addNewElement(new Person(-1L, scanner.nextLine(),Integer.parseInt(scanner.nextLine()), Float.parseFloat(scanner.nextLine()),
                            formatter.parse(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()),
                            Person.Country.valueOf(scanner.nextLine()), Long.parseLong(scanner.nextLine()), Float.parseFloat(scanner.nextLine()), Float.parseFloat(scanner.nextLine())));
                } else {
                    if (command.getClass().toString().equals("Commands.UpdateID")) {
                        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
                        Command removeID = new RemoveByID(command.argument);
                        removeID.execute(datebase);
                        datebase.addNewElement(new Person(Long.parseLong(command.argument), scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Float.parseFloat(scanner.nextLine()),
                                formatter.parse(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()),
                                Person.Country.valueOf(scanner.nextLine()), Long.parseLong(scanner.nextLine()), Float.parseFloat(scanner.nextLine()), Float.parseFloat(scanner.nextLine())));
                    } else
                        command.execute(datebase);
                }
            }
        } catch (IOException fileNotFoundException){
            System.out.println("No such file");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
