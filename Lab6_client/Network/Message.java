package Network;

import Commands.Command;
import com.company.Person;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
    private Status status;

    public Message(Command command, Status status){
        this.command = command;
        this.status = status;
    }

    public Command getCommand() {
        return command;
    }

    public Status getStatus() {
        return status;
    }
}
