package Network;

import Commands.Command;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
    private Status status;
    private int port;

    public Message(Command command, Status status){
        this.command = command;
        this.status = status;
    }

    public Command getCommand() {
        return command;
    }

    public int getPort() {
        return port;
    }

    public Status getStatus() {
        return status;
    }
}
