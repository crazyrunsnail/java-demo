package person.davino.dp.command;

/**
 * 相当于 Client, 只需要调同一个方法就可以了
 */

public class SimpleRemoteTest {

    public static void main(String[] args) {
        SimpleRemoteController controller = new SimpleRemoteController();
        controller.setCommand(new LightOnCommand(new Light()));
        controller.buttonWasPressed();

        controller.setCommand(new GarageDoorOpenCommand(new GarageDoor()));
        controller.buttonWasPressed();
    }
}
