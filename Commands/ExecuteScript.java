package Commands;

import Exceptions.RecursionException;
import com.company.Database;
import com.company.InputReader;
import com.company.Person;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Executes script, every command starts with new line
 */
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
                if (command.getClass().toString().equals("class Commands.ExecuteScript")&&command.argument.equals(argument)){
                    throw new RecursionException();
                }
                if (command.getClass().toString().equals("class Commands.Add")){
                    datebase.addNewElement(new Person(datebase.generateID(), scanner.nextLine(),Integer.parseInt(scanner.nextLine()), Float.parseFloat(scanner.nextLine()),
                           new Date(), Integer.parseInt(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()),
                            Person.Country.valueOf(scanner.nextLine()), Long.parseLong(scanner.nextLine()), Float.parseFloat(scanner.nextLine()), Float.parseFloat(scanner.nextLine())));
                } else {
                    if (command.getClass().toString().equals("class Commands.UpdateID")) {
                        Command removeID = new RemoveByID(command.argument);
                        removeID.execute(datebase);
                        datebase.addNewElement(new Person(Long.parseLong(command.argument), scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Float.parseFloat(scanner.nextLine()),
                                new Date(), Integer.parseInt(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()), Person.Color.valueOf(scanner.nextLine()),
                                Person.Country.valueOf(scanner.nextLine()), Long.parseLong(scanner.nextLine()), Float.parseFloat(scanner.nextLine()), Float.parseFloat(scanner.nextLine())));
                    } else
                        command.execute(datebase);
                }
            }
            System.out.println("Script successfully executed");
        } catch (IOException fileNotFoundException){
            System.out.println("No such file");
        } catch (NumberFormatException numberFormatException){
            System.out.println("File is incorrect");
        } catch (NoSuchElementException exception){
            System.out.println("nsel");
        }
        catch (RecursionException recursionException){
            System.out.println("Recursion detected, interrupting");
        }

    }
}
