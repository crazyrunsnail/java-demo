package person.davino.dp.observe.insidejdk;

import person.davino.dp.observe.improve.Display;
import person.davino.dp.observe.improve.Subject;

import java.util.Observable;
import java.util.Observer;

public class ConcreteDisplay implements Display, Observer {

    private Observable weatherData;

    public ConcreteDisplay(Observable weatherData) {
        this.weatherData = weatherData;
        weatherData.addObserver(this);
    }

    @Override
    public void display() {

    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            //
        }
    }
}
