package com.company;

import java.util.NoSuchElementException;
import java.util.Scanner;
import Commands.*;
import Commands.Error;

public class InputReader {

    String argumentAfterCommand(String[] input_arr){
        int i = 1;
        String argument = null;
        while(i<input_arr.length && argument == null){
            if(!input_arr[i].equals(" ")){
                argument = input_arr[i];
            }
            i+=1;
        }
        return argument;
    }
    public Command read(Scanner scanner) {
        Command cmd;
        try {
            String input = scanner.nextLine();
            String[] input_arr = input.split(" ");
            String command = input_arr[0];

            switch (command) {
                case ("help"):
                    cmd = new Help(null);
                    break;
                case ("info"):
                    cmd = new Info(null);
                    break;
                case ("show"):
                    cmd = new Show(null);
                    break;
                case ("add"):
                    cmd = new Add(argumentAfterCommand(input_arr));
                    break;
                case ("update"):
                    int i = 0;
                    boolean _id = false;
                    while (i < input_arr.length && !_id) {
                        if (input_arr[i].equals("id")) {
                            _id = true;
                            input_arr[i] = " ";
                        }
                        i += 1;
                    }
                    if (_id) {
                        cmd = new UpdateID(argumentAfterCommand(input_arr));
                        break;
                    } else {
                        cmd = new Error(null);
                        System.out.println("Unknown command, try help");
                    }

                    break;
                case ("remove_by_id"):
                    cmd = new RemoveByID(argumentAfterCommand(input_arr));
                    break;
                case ("clear"):
                    cmd = new Clear(null);
                    break;
                case ("save"):
                    cmd = new Save(null);
                    break;
                case ("execute_script"):
                    cmd = new ExecuteScript(argumentAfterCommand(input_arr));
                    break;
                case ("exit"):
                    cmd = new Exit(null);
                    break;
                case ("remove_greater"):
                    cmd = new RemoveGreater(argumentAfterCommand(input_arr));
                    break;
                case ("remove_lower"):
                    cmd = new RemoveLower(argumentAfterCommand(input_arr));
                    break;
                case ("history"):
                    cmd = new History(null);
                    break;
                case ("sum_of_height"):
                    cmd = new SumOfHeight(null);
                    break;
                case ("filter_starts_with_name"):
                    cmd = new FilterStartsWithName(argumentAfterCommand(input_arr));
                    break;
                case ("filter_less_than_location"):
                    cmd = new FilterLessThanLocation(argumentAfterCommand(input_arr));
                    break;
                default:
                    cmd = new Error(null);
            }
        } catch (NoSuchElementException exception){ cmd = new Error(null);
            System.exit(0);}
        return cmd;
    }
}
