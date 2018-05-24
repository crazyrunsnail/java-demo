package person.davino.dp.observe.improve;

public class ConcreteDisplay implements Display, Observer{

    private Subject weatherData;

    public ConcreteDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {

    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.display();
    }
}
