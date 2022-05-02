package Commands;

import Exceptions.RecursionException;
import com.company.Database;
import com.company.InputReader;
import com.company.Person;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Executes script, every command starts with new line
 */
public class ExecuteScript extends Command {
    LinkedList<Command> commands = new LinkedList<>();
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
                if (command.getClass().toString().equals("class Commands.ExecuteScript")&&command.argument.equals(argument)){
                    commands.add(new Error("Recursion detected"));
                }
                if (command.getClass().toString().equals("class Commands.Add")){
                    Person person = new Person(datebase.generateID(), scanner.nextLine(),Integer.parseInt(scanner.nextLine()), Float.parseFloat(scanner.nextLine()),
                           new Date(), Integer.parseInt(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()),
                            Person.Country.valueOf(scanner.nextLine()), Long.parseLong(scanner.nextLine()), Float.parseFloat(scanner.nextLine()), Float.parseFloat(scanner.nextLine()));
                    Add add = (Add) command;
                    add.setPerson(person);
                    commands.add(add);
                } else {
                    if (command.getClass().toString().equals("class Commands.UpdateID")) {
                        UpdateID updateByID= new UpdateID(command.argument);
                        Person person = new Person(Long.parseLong(command.argument), scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Float.parseFloat(scanner.nextLine()),
                                new Date(), Integer.parseInt(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()),
                                Person.Country.valueOf(scanner.nextLine()), Long.parseLong(scanner.nextLine()), Float.parseFloat(scanner.nextLine()), Float.parseFloat(scanner.nextLine()));
                        updateByID.setPerson(person);
                        commands.add(updateByID);
                    } else
                        commands.add(command);
                }
            }
        } catch (IOException fileNotFoundException){
            System.out.println("No such file");
        } catch (NumberFormatException numberFormatException){
            System.out.println("File is incorrect");
        } catch (NoSuchElementException exception){
            System.out.println("nsel");
        }

    }
}
