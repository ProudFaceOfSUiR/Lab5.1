package Network;

import Commands.Command;
import Security.LoginController;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
    private Status status;
    private int port;
    private LoginController login;

    public Message(Command command, Status status, int port){
        this.command = command;
        this.status = status;
        this.port = port;
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
