package Network;

import Commands.Command;
import com.company.Person;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
    private Person person;
    private Status status;

    public Message(Command command, Person person, Status status){
        this.command = command;
        this.person = person;
        this.status = status;
    }

    public Command getCommand() {
        return command;
    }

    public Person getPerson() {
        return person;
    }

    public Status getStatus() {
        return status;
    }
}
