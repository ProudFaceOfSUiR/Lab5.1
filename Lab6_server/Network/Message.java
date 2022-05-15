package Network;

import Commands.Command;
import Security.LoginController;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
    private Status status;
    private int port;
    private LoginController login;

    public Message(Command command, Status status){
        this.command = command;
        this.status = status;
    }

    public void setLogin(LoginController login) {
        this.login = login;
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

    public LoginController getLogin() {
        return login;
    }
}
