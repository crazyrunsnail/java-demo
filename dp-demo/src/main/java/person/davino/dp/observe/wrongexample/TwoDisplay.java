package person.davino.dp.observe.wrongexample;

public class TwoDisplay implements Display{
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("Two");
    }
}
