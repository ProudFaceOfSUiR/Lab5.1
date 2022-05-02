package Network;

import Commands.Command;
import com.company.Person;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
    private Status status;
    private int port = 228;

    public Message(Command command, Status status){
        this.command = command;
        this.status = status;
    }

    public int getPort() {return port;}

    public Command getCommand() {
        return command;
    }

    public Status getStatus() {
        return status;
    }
}
