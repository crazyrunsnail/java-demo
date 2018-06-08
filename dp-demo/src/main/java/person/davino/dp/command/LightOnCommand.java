package person.davino.dp.command;

public class LightOnCommand implements Command{

    private Light light; // Receiver 接收者

    public LightOnCommand(Light light) {
        this.light = light;
    }



    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
