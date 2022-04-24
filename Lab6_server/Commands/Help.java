package Commands;

import com.company.Database;

import java.io.Serializable;

/**
 * Just help
 */
public class Help extends Command implements Serializable {
    public Help(String argument) {
        super(argument);
    }

    @Override
    public void execute(Database database) {
        answer = "Display information about builtin commands\n\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 15 команд (без их аргументов)\n" +
                "sum_of_height : вывести сумму значений поля height для всех элементов коллекции\n" +
                "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                "filter_less_than_location location : вывести элементы, значение поля location которых меньше заданного";
        //System.out.println(helpMessage);
        //answer = helpMessage;
    }
}
