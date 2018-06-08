package person.davino.dp.command;

/**
 * 命令模式中的 Invoker, 调用 execute
 */
public class SimpleRemoteController {
    private Command command;

    public SimpleRemoteController(Command command) {
        this.command = command;
    }

    public SimpleRemoteController() {
    }

    public void buttonWasPressed() {
        command.execute();
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
