package com.company;

import Commands.Command;
import com.company.Database;
import com.company.InputReader;

import java.util.Scanner;

public class Runtime {
    public static void start(){
        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        Database Database = new Database();
        Database.initialize();

        while(true) {
            Command command = inputReader.read(scanner);
            command.execute(Database);
            Database.updateHistoryLog(command);
        }
    }
}
