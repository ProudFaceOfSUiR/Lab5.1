import com.company.CommandEnum;

public class CommandHandler {
    public CommandEnum command;
    public Object argument;
    public CommandHandler(CommandEnum command, Object argument){
        this.argument = argument;
        this.command = command;
    }

    @Override
    public String toString() {
        return "CommandHandler{" +
                "command=" + command +
                ", argument=" + argument +
                '}';
    }
}
